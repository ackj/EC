package cn.itsite.albs.location;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.TranslateAnimation;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.itsite.abase.BaseApp;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;
import cn.itsite.abase.mvp.view.base.Decoration;
import cn.itsite.albs.R;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author leguang
 * @version v0.0.0
 * @E-mail langmanleguang@qq.com
 * @time 2018/2/26 0026 19:25
 */

public class LocationFragment extends BaseFragment implements
        LocationSource, AMapLocationListener, GeocodeSearch.OnGeocodeSearchListener, PoiSearch.OnPoiSearchListener {
    public static final String TAG = LocationFragment.class.getSimpleName();
    public static final String POI = "poi";
    public static final int REQUEST_CODE = 100;
    private CardView cvSearch;
    private TextView tvKeword;
    private TextView tvLocation;
    private ImageView ivBack;
    private RecyclerView recyclerView;
    private AMap aMap;
    private TextView tvSearch;
    private MapView mapView;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    private Marker locationMarker;
    private GeocodeSearch geocoderSearch;
    // Poi查询条件类
    private PoiSearch.Query query;
    private PoiSearch poiSearch;
    // poi数据
    private List<PoiItem> poiItems;
    //住宅区类型
    private String searchType = "120300";
    private LatLonPoint searchLatlonPoint;
    private ImageView myLocation;
    private LatLng currentLatlng;
    private BaseRecyclerViewAdapter<PoiItem, BaseViewHolder> adapter;
    private Tip tip;
    private AMapLocation amapLocation;

    public static LocationFragment newInstance() {
        return new LocationFragment();
    }

    public static LocationFragment newInstance(Bundle bundle) {
        LocationFragment fragment = new LocationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        tvKeword = view.findViewById(R.id.keyWord);
        tvSearch = view.findViewById(R.id.bt_search);
        recyclerView = view.findViewById(R.id.recyclerView);
        myLocation = view.findViewById(R.id.iv_location_fragment);
        ivBack = view.findViewById(R.id.iv_back_location_fragment);
        cvSearch = view.findViewById(R.id.cv_search_location_fragment);
        tvLocation = view.findViewById(R.id.tv_location_fragment);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initMap();
    }

    private void initView() {
        cvSearch.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable(POI, amapLocation);
            startForResult(SearchFragment.newInstance(bundle), REQUEST_CODE);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.addItemDecoration(new Decoration(_mActivity, Decoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter = new BaseRecyclerViewAdapter<PoiItem, BaseViewHolder>(R.layout.item_poi) {
            @Override
            protected void convert(BaseViewHolder helper, PoiItem item) {
                helper.setText(R.id.tv_title_item_poi, item.getTitle())
                        .setText(R.id.tv_description_item_poi, item.getProvinceName() + item.getCityName() + item.getAdName() + item.getSnippet())
                        .setVisible(R.id.iv_clear_item_poi, false);
            }
        });

        adapter.setOnItemClickListener((adapter, view, position) -> {
            PoiItem item = ((PoiItem) adapter.getData().get(position));
            String address = item.getProvinceName() + item.getCityName() + item.getAdName() + item.getSnippet();
            selectAddress(address);
        });

        myLocation.setOnClickListener(v -> aMap.moveCamera(CameraUpdateFactory.changeLatLng(currentLatlng)));

        ivBack.setOnClickListener(v -> _mActivity.onBackPressed());

        tvLocation.setOnClickListener(v -> {
            String address = tvLocation.getText().toString();
            if (address.startsWith(BaseApp.mContext.getString(R.string.locating))) {
                DialogHelper.warningSnackbar(getView(), "抱歉，未获取到定位，请重新尝试！");
            } else {
                selectAddress(address);
            }
        });
    }

    private void initData() {
        geocoderSearch = new GeocodeSearch(BaseApp.mContext);
        geocoderSearch.setOnGeocodeSearchListener(this);
    }

    /**
     * 初始化
     */
    private void initMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.getUiSettings().setZoomControlsEnabled(false);
            aMap.getUiSettings().setLogoBottomMargin(-100);
            aMap.setLocationSource(this);// 设置定位监听
            aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
            aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
            aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
            aMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        }

        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                searchLatlonPoint = new LatLonPoint(cameraPosition.target.latitude, cameraPosition.target.longitude);
                startJumpAnimation();
                geoAddress();
                Log.e(TAG, searchLatlonPoint.toString());
            }
        });

        aMap.setOnMapLoadedListener(() -> addMarkerInScreenCenter(null));
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        deactivate();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if (null != mLocationClient) {
            mLocationClient.onDestroy();
        }
    }

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        this.amapLocation = amapLocation;
        if (mListener != null && amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);
                Log.e(TAG, "amapLocation-->" + amapLocation.toString());
                currentLatlng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                searchLatlonPoint = new LatLonPoint(currentLatlng.latitude, currentLatlng.longitude);
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatlng, 16));
            } else {
                String error = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                Log.e(TAG, "AmapErr" + error);
                DialogHelper.warningSnackbar(getView(), "定位失败，请检查网络或者打开GPS！");
            }
        }
    }

    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mLocationClient == null) {
            mLocationClient = new AMapLocationClient(BaseApp.mContext);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mLocationClient.setLocationListener(this);
            //设置为单次定位
            mLocationOption.setOnceLocation(true);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mLocationClient.startLocation();
        } else {
            mLocationClient.startLocation();
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }


    /**
     * 响应逆地理编码
     */
    public void geoAddress() {
        showLoading("加载中…");
//        tvKeword.setText("");
        if (searchLatlonPoint != null) {
            RegeocodeQuery query = new RegeocodeQuery(searchLatlonPoint, 200, GeocodeSearch.AMAP);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
            geocoderSearch.getFromLocationAsyn(query);
        }
    }

    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery() {
        showLoading("搜索中…");
        query = new PoiSearch.Query("", searchType, "");// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setCityLimit(true);
        query.setPageSize(20);
        query.setPageNum(0);//当前页，从0开始。

        if (searchLatlonPoint != null) {
            poiSearch = new PoiSearch(BaseApp.mContext, query);
            poiSearch.setOnPoiSearchListener(this);
            poiSearch.setBound(new PoiSearch.SearchBound(searchLatlonPoint, 1000, true));//
            poiSearch.searchPOIAsyn();
        }
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getRegeocodeAddress() != null
                    && result.getRegeocodeAddress().getFormatAddress() != null) {
                String address = result.getRegeocodeAddress().getFormatAddress();
                Log.e(TAG, "FormatAddress-->" + address);
                tvLocation.setText(address);
                doSearchQuery();
            }
        } else {
            Log.e(TAG, "error code is " + rCode);
            DialogHelper.warningSnackbar(getView(), "定位失败，请检查网络或者打开GPS！");
        }
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }

    /**
     * POI搜索结果回调
     *
     * @param poiResult  搜索结果
     * @param resultCode 错误码
     */
    @Override
    public void onPoiSearched(PoiResult poiResult, int resultCode) {
        dismissLoading();
        if (resultCode == AMapException.CODE_AMAP_SUCCESS) {
            if (poiResult != null && poiResult.getQuery() != null) {
                if (poiResult.getQuery().equals(query)) {
                    poiItems = poiResult.getPois();
                    Log.e(TAG, "poiItems.size()-->" + poiItems.size());
                    adapter.setNewData(poiItems);
                }
            } else {
                Log.e(TAG, "error code is " + resultCode);
            }
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    private void addMarkerInScreenCenter(LatLng locationLatLng) {
        LatLng latLng = aMap.getCameraPosition().target;
        Point screenPosition = aMap.getProjection().toScreenLocation(latLng);
        locationMarker = aMap.addMarker(new MarkerOptions()
                .anchor(0.5F, 0.5F)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_red_24dp)));
        //设置Marker在屏幕上,不跟随地图移动
        locationMarker.setPositionByPixels(screenPosition.x, screenPosition.y);
        locationMarker.setZIndex(1);
    }

    /**
     * 屏幕中心marker 跳动
     */
    public void startJumpAnimation() {

        if (locationMarker != null) {
            //根据屏幕距离计算需要移动的目标点
            final LatLng latLng = locationMarker.getPosition();
            Point point = aMap.getProjection().toScreenLocation(latLng);
            point.y -= dip2px(BaseApp.mContext, 125);
            LatLng target = aMap.getProjection()
                    .fromScreenLocation(point);
            //使用TranslateAnimation,填写一个需要移动的目标点
            Animation animation = new TranslateAnimation(target);
            animation.setInterpolator(input -> {
                // 模拟重加速度的interpolator
                if (input <= 0.5) {
                    return (float) (0.5f - 2 * (0.5 - input) * (0.5 - input));
                } else {
                    return (float) (0.5f - Math.sqrt((input - 0.5f) * (1.5f - input)));
                }
            });
            //整个移动所需要的时间
            animation.setDuration(600);
            //设置动画
            locationMarker.setAnimation(animation);
            //开始动画
            locationMarker.startAnimation();

        } else {
            Log.e(TAG, "screenMarker is null");
        }
    }

    //dip和px转换
    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

    private void searchPOI(Tip tip) {
        if (tip == null) {
            return;
        }
        searchLatlonPoint = tip.getPoint();
        if (searchLatlonPoint != null) {
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(searchLatlonPoint.getLatitude(), searchLatlonPoint.getLongitude()), 16F));
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        tip = data.getParcelable(SearchFragment.TIP);
        tvKeword.setText(tip == null ? "" : tip.getName());
        searchPOI(tip);
    }

    private void selectAddress(String address) {
        Bundle bundle = new Bundle();
        bundle.getString(POI, address);
        setFragmentResult(SupportFragment.RESULT_OK, bundle);
        _mActivity.onBackPressed();
    }
}
