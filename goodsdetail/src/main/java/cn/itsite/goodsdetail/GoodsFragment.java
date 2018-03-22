package cn.itsite.goodsdetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.goodsdetail.contract.ProductContract;
import cn.itsite.goodsdetail.presenter.ProductPresenter;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/21 0021 14:41
 */

public class GoodsFragment extends BaseFragment<ProductContract.Presenter> implements ProductContract.View {

    public static final String TAG = GoodsFragment.class.getSimpleName();
    private Banner mBanner;
    private TextView mTvName;
    private TextView mTvDesc;
    private TextView mTvPrice;
    private FlexboxLayout mFlexboxLayout;

    public static GoodsFragment newInstance() {
        return new GoodsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    protected ProductContract.Presenter createPresenter() {
        return new ProductPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods, container, false);
        mBanner = view.findViewById(R.id.banner);
        mTvName = view.findViewById(R.id.tv_name);
        mTvDesc = view.findViewById(R.id.tv_desc);
        mTvPrice = view.findViewById(R.id.tv_price);
        mFlexboxLayout = view.findViewById(R.id.flexboxLayout);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initStatusBar();
        initData();
        initListener();
    }

    private void initStatusBar() {
    }

    private void initData() {
        mPresenter.getProduct("123");
    }

    private void initListener() {
    }

    @Override
    public void responseGetProduct(ProductDetailBean bean) {
        refreshBanner(bean);
        refreshLables(bean);
        mTvName.setText(bean.getTitle());
        mTvDesc.setText(bean.getDescription());
        mTvDesc.setText(bean.getPay().getCurrency() + bean.getPay().getCost());
    }

    private void refreshLables(ProductDetailBean bean) {
        for (int i = 0; i < bean.getAttributes().size(); i++) {
            LinearLayout llLable = (LinearLayout) LayoutInflater.from(_mActivity).inflate(R.layout.view_lable, null);
            mFlexboxLayout.addView(llLable);
        }
    }

    private void refreshBanner(ProductDetailBean bean) {
        List<ProductDetailBean.ImagesBean> images = bean.getImages();
        List<Object> bannerImages = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            bannerImages.add(images.get(i).getImage());
        }
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        })
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setImages(bannerImages)
                .isAutoPlay(true)
                .start();
    }
}
