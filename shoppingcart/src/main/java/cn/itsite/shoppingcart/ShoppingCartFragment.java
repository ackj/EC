package cn.itsite.shoppingcart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.ScreenUtils;

/**
 * Author： Administrator on 2018/1/31 0031.
 * Email： liujia95me@126.com
 */
@Route(path = "/shoppingcart/shoppingcartfragment")
public class ShoppingCartFragment extends BaseFragment {

    public static final String TAG = ShoppingCartFragment.class.getSimpleName();

    private RelativeLayout mRlToolbar;
    private RecyclerView mRecyclerView;
    private ShoppingCartRVAdapter mAdapter;

    public static ShoppingCartFragment newInstance() {
        return new ShoppingCartFragment();
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

        final List<ShoppingCartBean> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ShoppingCartBean storeTitle = new ShoppingCartBean();
            storeTitle.setItemType(ShoppingCartBean.TYPE_STORE_TITLE);
            storeTitle.setSpanSize(2);
            data.add(storeTitle);

            for (int j = 0; j < 5; j++) {
                ShoppingCartBean storeGoods = new ShoppingCartBean();
                storeGoods.setItemType(ShoppingCartBean.TYPE_STORE_GOODS);
                storeGoods.setSpanSize(2);
                data.add(storeGoods);
            }
        }
        ShoppingCartBean recommendTitle = new ShoppingCartBean();
        recommendTitle.setItemType(ShoppingCartBean.TYPE_RECOMMEND_TITLE);
        recommendTitle.setSpanSize(2);
        data.add(recommendTitle);
        for (int i = 0; i < 10; i++) {
            ShoppingCartBean goods = new ShoppingCartBean();
            goods.setItemType(ShoppingCartBean.TYPE_RECOMMEND_GOODS);
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

    }


}
