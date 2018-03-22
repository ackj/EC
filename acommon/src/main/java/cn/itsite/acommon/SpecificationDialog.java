package cn.itsite.acommon;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.utils.DensityUtils;
import cn.itsite.acommon.contract.SkusContract;
import cn.itsite.acommon.presenter.SkusPresenter;
import cn.itsite.adialog.dialog.LoadingDialog;
import cn.itsite.adialog.dialogfragment.BaseDialogFragment;

/**
 * Author： Administrator on 2018/2/7 0007.
 * Email： liujia95me@126.com
 */

public class SpecificationDialog extends BaseDialogFragment implements SkusContract.View {

    private RecyclerView mRecyclerView;
    private GoodsCounterView mTvGoodsCounter;

    private SkusPresenter mPresenter = new SkusPresenter(this);
    private LoadingDialog loadingDialog;
    private SpecificationRVAdapter mAdapter;
    private TextView mTvName;
    private TextView mTvStockQuantity;
    private TextView mTvSku;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_specification, null);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mTvGoodsCounter = view.findViewById(R.id.view_goods_counter);
        mTvName = view.findViewById(R.id.tv_name);
        mTvSku = view.findViewById(R.id.tv_sku);
        mTvStockQuantity = view.findViewById(R.id.tv_stock_quantity);
        return view;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setGravity(Gravity.BOTTOM);
        setAnimStyle(R.anim.slide_enter);
        initData();
    }

    private void initData() {
        mTvGoodsCounter.setCountWidth(DensityUtils.dp2px(getContext(), 65));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new SpecificationRVAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.getSkus("123");
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void showLoading(String message) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(getContext());
            loadingDialog.setDimAmount(0);
        } else {
            loadingDialog.setText(message);
        }
        loadingDialog.show();
    }

    public void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void start(Object response) {
        showLoading("玩命加载中...");
    }

    @Override
    public void error(String errorMessage) {
        dismissLoading();
        DialogHelper.errorSnackbar(getView(), errorMessage);
    }

    @Override
    public void complete(Object response) {
        dismissLoading();
    }

    @Override
    public void responseGetSkus(SkusBean bean) {
        mAdapter.setNewData(bean.getAttributes());
    }
}
