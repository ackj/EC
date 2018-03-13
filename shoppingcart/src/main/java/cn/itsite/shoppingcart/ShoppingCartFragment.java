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

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.ScreenUtils;
import cn.itsite.acommon.SpecificationDialog;
import cn.itsite.shoppingcart.contract.CartContract;
import cn.itsite.shoppingcart.presenter.CartPresenter;

/**
 * Author： Administrator on 2018/1/31 0031.
 * Email： liujia95me@126.com
 */
@Route(path = "/shoppingcart/shoppingcartfragment")
public class ShoppingCartFragment extends BaseFragment<CartContract.Presenter> implements CartContract.View {

    public static final String TAG = ShoppingCartFragment.class.getSimpleName();

    private RelativeLayout mRlToolbar;
    private RecyclerView mRecyclerView;
    private ShoppingCartRVAdapter mAdapter;
    private CheckBox mCbSelectAll;

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

        final List<ShoppingCartGridBean> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ShoppingCartGridBean storeTitle = new ShoppingCartGridBean();
            storeTitle.setItemType(ShoppingCartGridBean.TYPE_STORE_TITLE);
            storeTitle.setGoodsCount(5);
            storeTitle.setSpanSize(2);
            data.add(storeTitle);

            for (int j = 0; j < 5; j++) {
                ShoppingCartGridBean storeGoods = new ShoppingCartGridBean();
                storeGoods.setItemType(ShoppingCartGridBean.TYPE_STORE_GOODS);
                storeGoods.setSpanSize(2);
                data.add(storeGoods);
            }
        }
        ShoppingCartGridBean recommendTitle = new ShoppingCartGridBean();
        recommendTitle.setItemType(ShoppingCartGridBean.TYPE_RECOMMEND_TITLE);
        recommendTitle.setSpanSize(2);
        data.add(recommendTitle);
        for (int i = 0; i < 10; i++) {
            ShoppingCartGridBean goods = new ShoppingCartGridBean();
            goods.setItemType(ShoppingCartGridBean.TYPE_RECOMMEND_GOODS);
            goods.setSpanSize(1);
            data.add(goods);
        }
        mAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return data.get(position).getSpanSize();
            }
        });
        mAdapter.setNewData(data);
    }

    private void initListener() {
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingCartGridBean item = mAdapter.getItem(position);
                switch (item.getItemType()) {
                    case ShoppingCartGridBean.TYPE_STORE_TITLE:
                        break;
                    case ShoppingCartGridBean.TYPE_STORE_GOODS:
                        if(view.getId() == R.id.tv_specification){
                            showSpecificationDialog();
                        }else if(view.getId()==R.id.tv_confirm){
                            mPresenter.deleteCart("123");
                        }
                        break;
                    case ShoppingCartGridBean.TYPE_RECOMMEND_TITLE:
                        break;
                    case ShoppingCartGridBean.TYPE_RECOMMEND_GOODS:
                        Fragment goodsDetailFragment = (Fragment) ARouter.getInstance().build("/goodsdetail/goodsdetailfragment").navigation();
                        start((BaseFragment) goodsDetailFragment);
                        break;
                    default:
                }
            }
        });

        mCbSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                List<ShoppingCartGridBean> data = mAdapter.getData();
                for (int i = 0; i < data.size(); i++) {
                    ShoppingCartGridBean bean = data.get(i);
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
        List<ShoppingCartGridBean> data = mAdapter.getData();
        ShoppingCartGridBean bean = mAdapter.getData().get(position);
        for (int i = position; i <= bean.getGoodsCount() + position; i++) {
            data.get(i).setChecked(isChecked);
        }
        mAdapter.notifyItemRangeChanged(position, bean.getGoodsCount() + 1);
    }
}
