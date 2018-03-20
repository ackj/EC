package cn.itsite.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.ScreenUtils;
import cn.itsite.order.contract.OrderDetailContract;
import cn.itsite.order.presenter.OrderDetailPresenter;

/**
 * Author： Administrator on 2018/2/1 0001.
 * Email： liujia95me@126.com
 */
@Route(path = "/order/orderdetailfragment")
public class OrderDetailFragment extends BaseFragment<OrderDetailContract.Presenter> implements OrderDetailContract.View {

    public static final String TAG = OrderDetailFragment.class.getSimpleName();
    private OrderDetailRVAdapter mAdapter;

    private RelativeLayout mRlToolbar;
    private RecyclerView mRecyclerView;
    private TextView mTvDeliveryType;
    private TextView mTvShopName;
    private TextView mTvCategory;
    private TextView mTvAmount;
    private TextView mTvContactWay;
    private TextView mTvLocation;
    private TextView mTvLeaveWords;
    private TextView mTvOrderNum;
    private TextView mTvOrderTime;

    public static OrderDetailFragment newInstance() {
        return new OrderDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    protected OrderDetailContract.Presenter createPresenter() {
        return new OrderDetailPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);
        mRlToolbar = view.findViewById(R.id.rl_toolbar);
        mRecyclerView = view.findViewById(R.id.recyclerView);
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
        mAdapter = new OrderDetailRVAdapter();
        //加header和footer
        View viewHeader = LayoutInflater.from(_mActivity).inflate(R.layout.item_order_detail_header, null);
        mTvDeliveryType = viewHeader.findViewById(R.id.tv_delivery_type);
        mTvShopName = viewHeader.findViewById(R.id.tv_name);
        mTvCategory = viewHeader.findViewById(R.id.tv_category);
        mAdapter.addHeaderView(viewHeader);

        View viewFooter = LayoutInflater.from(_mActivity).inflate(R.layout.item_order_detail_footer, null);
        mTvAmount = viewFooter.findViewById(R.id.tv_amount);
        mTvContactWay = viewFooter.findViewById(R.id.tv_contactway);
        mTvLocation = viewFooter.findViewById(R.id.tv_location);
        mTvLeaveWords = viewFooter.findViewById(R.id.tv_leave_words);
        mTvOrderNum = viewFooter.findViewById(R.id.tv_order_num);
        mTvOrderTime = viewFooter.findViewById(R.id.tv_order_time);
        mAdapter.addFooterView(viewFooter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.getOrderDetail("123");
    }

    private void initListener() {
    }


    @Override
    public void responseOrderDetail(OrderDetailBean orderDetailBean) {
        mTvDeliveryType.setText(orderDetailBean.getDeliveryType());
        mTvShopName.setText(orderDetailBean.getShop().getName());
        mTvCategory.setText(orderDetailBean.getCategory());
        mTvAmount.setText(_mActivity.getString(R.string.amount, orderDetailBean.getAmount(), "￥ " + orderDetailBean.getCost()));
        mTvContactWay.setText(_mActivity.getString(R.string.consignee, orderDetailBean.getDelivery().getName(), orderDetailBean.getDelivery().getPhoneNumber()));
        mTvLocation.setText(orderDetailBean.getDelivery().getAddress() + orderDetailBean.getDelivery().getLocation());
        mTvLeaveWords.setText(orderDetailBean.getNote());
        mTvOrderNum.setText(orderDetailBean.getOrderNumber());
        mTvOrderTime.setText(orderDetailBean.getTime());
        mAdapter.setNewData(orderDetailBean.getProducts());

    }
}
