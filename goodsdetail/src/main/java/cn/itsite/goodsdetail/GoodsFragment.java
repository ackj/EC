package cn.itsite.goodsdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.goodsdetail.contract.ProductContract;
import cn.itsite.goodsdetail.presenter.ProductPresenter;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/21 0021 14:41
 */

public class GoodsFragment extends BaseFragment<ProductContract.Presenter> implements ProductContract.View{

    public static final String TAG = GoodsFragment.class.getSimpleName();

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

    }
}
