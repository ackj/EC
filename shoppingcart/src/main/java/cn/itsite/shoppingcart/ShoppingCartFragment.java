package cn.itsite.shoppingcart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.ScreenUtils;
import cn.itsite.abase.utils.ToastUtils;
import cn.itsite.acommon.Params;
import cn.itsite.acommon.SpecificationDialog;
import cn.itsite.shoppingcart.contract.CartContract;
import cn.itsite.shoppingcart.presenter.CartPresenter;

/**
 * Author： Administrator on 2018/1/31 0031.
 * Email： liujia95me@126.com
 */
@Route(path = "/shoppingcart/shoppingcartfragment")
public class ShoppingCartFragment extends BaseFragment<CartContract.Presenter> implements CartContract.View, View.OnClickListener {

    public static final String TAG = ShoppingCartFragment.class.getSimpleName();

    private RelativeLayout mRlToolbar;
    private RecyclerView mRecyclerView;
    private ShoppingCartRVAdapter mAdapter;
    private CheckBox mCbSelectAll;
    private TextView mTvSubmit;
    private TextView mTvTotalSum;
    private TextView mTvEdit;
    private TextView mTvAnchor;//锚，无需在意这个view
    //--------------------------
    private boolean isEditModel;//是编辑模式吗
//    private GoodsCounterView mCurrentCounterView;//当前计数的view
    List<StoreBean> mDatas = new ArrayList<>();
    private Params mParams =new Params();

    public static ShoppingCartFragment newInstance() {
        return new ShoppingCartFragment();
    }

    @NonNull
    @Override
    protected CartContract.Presenter createPresenter() {
        return new CartPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRlToolbar = view.findViewById(R.id.rl_toolbar);
        mCbSelectAll = view.findViewById(R.id.cb_select_all);
        mTvSubmit = view.findViewById(R.id.tv_submit);
        mTvTotalSum = view.findViewById(R.id.tv_total_sum);
        mTvEdit = view.findViewById(R.id.tv_edit);
        mTvAnchor = view.findViewById(R.id.anchor_1);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initStatusBar();
        initData();
        initListener();
    }

    private void initStatusBar() {
        mRlToolbar.setPadding(mRlToolbar.getPaddingLeft(), mRlToolbar.getPaddingTop() + ScreenUtils.getStatusBarHeight(_mActivity), mRlToolbar.getPaddingRight(), mRlToolbar.getPaddingBottom());
    }

    private void initData() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 2));
        mAdapter = new ShoppingCartRVAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return mDatas.get(position).getSpanSize();
            }
        });
        mAdapter.setNewData(mDatas);
        mPresenter.getCarts("123");
    }

    private void initListener() {
        mTvSubmit.setOnClickListener(this);
        mTvEdit.setOnClickListener(this);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                StoreBean item = mAdapter.getItem(position);
                switch (item.getItemType()) {
                    case StoreBean.TYPE_STORE_TITLE:
                        break;
                    case StoreBean.TYPE_STORE_GOODS:
                        if (view.getId() == R.id.tv_specification) {
                            showSpecificationDialog();
                        } else if (view.getId() == R.id.tv_confirm) {
                            mPresenter.putProduct("123",item.getProductsBean().getUid());
                        }
                        break;
                    case StoreBean.TYPE_RECOMMEND_TITLE:
                        break;
                    case StoreBean.TYPE_RECOMMEND_GOODS:
                        Fragment goodsDetailFragment = (Fragment) ARouter.getInstance().build("/goodsdetail/goodsdetailfragment").navigation();
                        start((BaseFragment) goodsDetailFragment);
                        break;
                    default:
                }
            }
        });

//        mAdapter.setOnAddMinusClickListener(new GoodsCounterView.OnAddMinusClickListener() {
//            @Override
//            public void clickAdd(GoodsCounterView view) {
//                mPresenter.putProduct("123", "123");
//                mCurrentCounterView = view;
//            }
//
//            @Override
//            public void clickMinus(GoodsCounterView view) {
//                mPresenter.putProduct("123", "123");
//                mCurrentCounterView = view;
//            }
//        });

        mCbSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                List<StoreBean> data = mAdapter.getData();
                for (int i = 0; i < data.size(); i++) {
                    StoreBean bean = data.get(i);
                    bean.setChecked(isChecked);
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        mAdapter.setOnCheckedChangedListener(new ShoppingCartRVAdapter.OnCheckedChangedListener() {
            @Override
            public void onStoreCheckedChanged(int position, boolean isChecked) {
                checkStoreGoods(position, isChecked);
            }

            @Override
            public void onGoodsCheckedChanged(int position, boolean isChecked) {
                mAdapter.getData().get(position).setChecked(isChecked);
            }
        });
    }

    private void showSpecificationDialog() {
        SpecificationDialog dialog = new SpecificationDialog();
        dialog.show(getChildFragmentManager());
    }

    //刷新选中的商城商品
    private void checkStoreGoods(int position, boolean isChecked) {
        StoreBean bean = mAdapter.getData().get(position);
        for (int i = position; i <= bean.getGoodsCount() + position; i++) {
            mDatas.get(i).setChecked(isChecked);
        }
        mAdapter.notifyItemRangeChanged(position, bean.getGoodsCount() + 1);
    }

    @Override
    public void responseDeleteSuccess(List<UidBean> data) {
        DialogHelper.successSnackbar(getView(), "被你删除成功了");
//        mCurrentCounterView.clickMinus();
    }

    @Override
    public void responsePostSuccess(List<UidBean> data) {
        DialogHelper.successSnackbar(getView(), "被你添加成功了");
//        mCurrentCounterView.clickAdd();
    }

    @Override
    public void responsePutSuccess(List<UidBean> data) {
        DialogHelper.successSnackbar(getView(), "被你修改成功了");
    }

    @Override
    public void responseGetCartsSuccess(List<StoreBean> data) {
        mDatas = data;
        //查推荐
        mPresenter.getRecommendGoods(mParams);
    }

    @Override
    public void responseRecommendGoodsSuccess(List<StoreBean> data) {
        mDatas.addAll(data);
        mAdapter.setNewData(mDatas);
    }

    private void switchEditModel() {
        if (isEditModel) {
            mTvEdit.setText("编辑");
            mTvEdit.setTextColor(_mActivity.getResources().getColor(R.color.base_black));
            mTvSubmit.setText("结算");
            mTvSubmit.setBackgroundColor(_mActivity.getResources().getColor(R.color.warn));
            mTvAnchor.setVisibility(View.VISIBLE);
            mTvTotalSum.setVisibility(View.VISIBLE);
        } else {
            mTvEdit.setText("完成");
            mTvEdit.setTextColor(_mActivity.getResources().getColor(R.color.warn));
            mTvSubmit.setText("删除");
            mTvSubmit.setBackgroundColor(_mActivity.getResources().getColor(R.color.error));
            mTvAnchor.setVisibility(View.GONE);
            mTvTotalSum.setVisibility(View.GONE);
        }
        isEditModel = !isEditModel;
    }

    private void clickSubmit() {
        if (isEditModel) {
            //删除
            mPresenter.deleteProduct("123","123");
        } else {
            //结算
            ToastUtils.showToast(_mActivity, "结算");
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_edit) {
            switchEditModel();
        } else if (v.getId() == R.id.tv_submit) {
            clickSubmit();
        }
    }


}
