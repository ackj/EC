package cn.itsite.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.ScreenUtils;

/**
 * Author： Administrator on 2018/2/1 0001.
 * Email： liujia95me@126.com
 */

public class OrderDetailFragment extends BaseFragment {

    public static final String TAG = OrderDetailFragment.class.getSimpleName();
    private RelativeLayout mRlToolbar;
    private RecyclerView mRecyclerView;
    private OrderDetailRVAdapter mAdapter;

    public static OrderDetailFragment newInstance() {
        return new OrderDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);
        mRlToolbar = view.findViewById(R.id.ll_toolbar);
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
        mAdapter.addHeaderView(viewHeader);

        View viewFooter = LayoutInflater.from(_mActivity).inflate(R.layout.item_order_detail_footer, null);
        mAdapter.addFooterView(viewFooter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setAdapter(mAdapter);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("");
        }
        mAdapter.setNewData(data);
    }

    private void initListener() {
    }

}
