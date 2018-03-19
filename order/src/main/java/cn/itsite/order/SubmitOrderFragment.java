package cn.itsite.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.ScreenUtils;

/**
 * Author： Administrator on 2018/2/1 0001.
 * Email： liujia95me@126.com
 */
@Route(path = "/order/submitorderfragment")
public class SubmitOrderFragment extends BaseFragment{

    public static final String TAG = SubmitOrderFragment.class.getSimpleName();

    private RelativeLayout mLlToolbar;
    private RecyclerView mRecyclerView;
    private SubmitOrderRVAdapter mAdapter;

    public static SubmitOrderFragment newInstance() {
        return new SubmitOrderFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_submit_order, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mLlToolbar = view.findViewById(R.id.rl_toolbar);
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
        mLlToolbar.setPadding(mLlToolbar.getPaddingLeft(), mLlToolbar.getPaddingTop() + ScreenUtils.getStatusBarHeight(_mActivity), mLlToolbar.getPaddingRight(), mLlToolbar.getPaddingBottom());
    }

    private void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new SubmitOrderRVAdapter();
        mRecyclerView.setAdapter(mAdapter);

//        final List<SubmitOrderBean> data = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            SubmitOrderBean storeTitle = new SubmitOrderBean();
//            storeTitle.setItemType(SubmitOrderBean.TYPE_STORE_TITLE);
//            data.add(storeTitle);
//
//            for (int j = 0; j < 5; j++) {
//                SubmitOrderBean storeGoods = new SubmitOrderBean();
//                storeGoods.setItemType(SubmitOrderBean.TYPE_STORE_GOODS);
//                data.add(storeGoods);
//            }
//            SubmitOrderBean orderInfo = new SubmitOrderBean();
//            orderInfo.setItemType(SubmitOrderBean.TYPE_ORDER_INFO);
//            data.add(orderInfo);
//        }
//
//        mAdapter.setNewData(data);
//        mPresenter.getOrder(0);
    }

    private void initListener() {

    }

//    @Override
//    public void responseGetOrder(List<OrderBean> data) {
//        List<SubmitOrderBean> list = new ArrayList<>();
//        for (int i = 0; i < data.size(); i++) {
//            OrderBean bean = data.get(i);
//            SubmitOrderBean titleBean = new SubmitOrderBean();
//            titleBean.setItemType(SubmitOrderBean.TYPE_STORE_TITLE);
//            titleBean.setOrderInfoBean(bean);
//            list.add(titleBean);
//            for (int j = 0; j < bean.getProducts().size(); j++) {
//                SubmitOrderBean productBean = new SubmitOrderBean();
//                productBean.setItemType(SubmitOrderBean.TYPE_STORE_GOODS);
////                productBean.setProductsBean(bean.getProducts().get(j));
//                list.add(productBean);
//            }
//            SubmitOrderBean orderInfoBean = new SubmitOrderBean();
//            orderInfoBean.setItemType(SubmitOrderBean.TYPE_ORDER_INFO);
//            orderInfoBean.setOrderInfoBean(bean);
//            list.add(orderInfoBean);
//        }
//        mAdapter.setNewData(list);
//    }
}
