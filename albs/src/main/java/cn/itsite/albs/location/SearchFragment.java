package cn.itsite.albs.location;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;

import org.litepal.crud.DataSupport;

import java.util.List;

import cn.itsite.abase.BaseApp;
import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.mvp.view.base.Decoration;
import cn.itsite.abase.utils.KeyBoardUtils;
import cn.itsite.albs.R;
import cn.itsite.albs.entity.db.TipsHistoryData;
import me.yokeyword.fragmentation.SupportFragment;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author leguang
 * @version v0.0.0
 * @E-mail langmanleguang@qq.com
 * @time 2018/2/26 0026 19:25
 */

public class SearchFragment extends BaseFragment {
    public static final String TAG = SearchFragment.class.getSimpleName();
    public static final String TIP = "TIP";
    private static final int HISTORY_SIZE = 50;
    private ImageView ivBack;
    private RecyclerView recyclerView;
    private TextView tvSearch;
    private EditText etKeyword;
    private ImageView ivClear;
    private SearchAdapter adapter;
    private TextView tvCity;
    private AMapLocation amapLocation;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    public static SearchFragment newInstance(Bundle bundle) {
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            amapLocation = bundle.getParcelable(LocationFragment.POI);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ivBack = view.findViewById(R.id.iv_back_search_fragment);
        tvSearch = view.findViewById(R.id.tv_search_search_fragment);
        tvCity = view.findViewById(R.id.tv_city_search_fragment);
        etKeyword = view.findViewById(R.id.et_keyword_search_fragment);
        ivClear = view.findViewById(R.id.iv_clear_search_fragment);
        recyclerView = view.findViewById(R.id.recyclerView);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        requestHistory();
        KeyBoardUtils.showKeybord(etKeyword, BaseApp.mContext);
    }

    private void initData() {
        ivBack.setOnClickListener(v -> _mActivity.onBackPressed());

        tvCity.setText(amapLocation == null ? "城市" : amapLocation.getCity());

        etKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newText = s.toString().trim();
                if (newText.length() > 0) {
                    ivClear.setVisibility(View.VISIBLE);
                    search(newText, tvCity.getText().toString());
                } else {
                    ivClear.setVisibility(View.GONE);
                    requestHistory();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        etKeyword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                showLoading();
                search(etKeyword.getText().toString().trim(), tvCity.getText().toString());
            }
            return false;
        });

        ivClear.setOnClickListener(v -> etKeyword.getEditableText().clear());

        tvSearch.setOnClickListener(v -> {
            if (etKeyword.getText().length() > 0) {
                KeyBoardUtils.hideKeybord(etKeyword, BaseApp.mContext);
                showLoading();
                search(etKeyword.getText().toString().trim(), tvCity.getText().toString());
            } else {
                DialogHelper.warningSnackbar(getView(), "亲！请输入关键字再搜索哦！");
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.addItemDecoration(new Decoration(_mActivity, Decoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter = new SearchAdapter());
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.iv_clear_item_poi) {
                //这个必须在remove之前调用，否则会数组越界。
                delete((Tip) adapter.getData().get(position));
                adapter.remove(position);
            }
        });

        adapter.setOnItemClickListener((adapter, view, position) -> {
            Tip tip = ((Tip) adapter.getData().get(position));
            cacheHistory(tip);
            Bundle bundle = new Bundle();
            bundle.putParcelable(TIP, tip);
            setFragmentResult(SupportFragment.RESULT_OK, bundle);
            _mActivity.onBackPressed();
        });
    }

    private void search(String keyword, String city) {
        //第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索。
        InputtipsQuery inputquery = new InputtipsQuery(keyword, city);
        Inputtips inputTips = new Inputtips(BaseApp.mContext, inputquery);
        inputquery.setCityLimit(true);
        inputTips.setInputtipsListener((list, rCode) -> {
            dismissLoading();
            if (rCode == AMapException.CODE_AMAP_SUCCESS) {
                adapter.setNewData(list);
            } else {
                Log.e(TAG, "erroCode " + rCode);
                DialogHelper.warningSnackbar(getView(), "亲，搜索失败，可以重试一下哦！");
            }
        });
        inputTips.requestInputtipsAsyn();
    }

    @Override
    public void onDestroyView() {
        KeyBoardUtils.hideKeybord(getView(), BaseApp.mContext);
        super.onDestroyView();
    }

    private void requestHistory() {
        Observable.create((Observable.OnSubscribe<List<TipsHistoryData>>) subscriber -> {
            subscriber.onStart();
            try {
                List<TipsHistoryData> data = DataSupport.order("id desc").find(TipsHistoryData.class);
                subscriber.onNext(data);
            } catch (Exception e) {
                e.printStackTrace();
                subscriber.onError(e);
            }
            subscriber.onCompleted();
        }).flatMap(Observable::from).map(history -> {
            Tip tip = new Tip();
            tip.setName(history.getName());
            tip.setDistrict(history.getDescription());
            tip.setPostion(new LatLonPoint(history.getLatitude(), history.getLongitude()));
            tip.setTypeCode("-1");
            return tip;
        }).toList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tips -> {
                    adapter.setNewData(tips);
                });
    }

    public void cacheHistory(Tip tip) {
        int i = DataSupport.deleteAll(TipsHistoryData.class, "name = ?", tip.getName());
        Log.e(TAG, "delete-->" + i);
        if (DataSupport.count(TipsHistoryData.class) >= HISTORY_SIZE) {
            TipsHistoryData first = DataSupport.findFirst(TipsHistoryData.class);
            first.delete();
        }
        TipsHistoryData history = new TipsHistoryData();
        history.setName(tip.getName());
        history.setDescription(tip.getDistrict());
        history.setLongitude(tip.getPoint().getLongitude());
        history.setLatitude(tip.getPoint().getLatitude());
        history.save();
    }

    private void delete(Tip tip) {
        Log.e(TAG, tip.getName());
        DataSupport.deleteAllAsync(TipsHistoryData.class, "name = ?", tip.getName())
                .listen(rows -> Log.e(TAG, "rows-->" + rows));
    }

    private void deleteAll() {
        DataSupport.deleteAllAsync(TipsHistoryData.class)
                .listen(rows -> Log.e(TAG, "rows-->" + rows));
    }
}
