package cn.itsite.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.acommon.Params;
import cn.itsite.order.contract.OrderListContract;
import cn.itsite.order.presenter.OrderListPresenter;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Author： Administrator on 2018/2/1 0001.
 * Email： liujia95me@126.com
 */
@Route(path = "/order/orderlistfragment")
public class OrderListFragment extends BaseFragment<OrderListContract.Presenter> implements OrderListContract.View {

    public static final String TAG = OrderListFragment.class.getSimpleName();

    RecyclerView mRecyclerView;
    private OrderListRVAdapter mAdapter;
    private Params mParams = new Params();

    public static OrderListFragment newInstance() {
        return new OrderListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    protected OrderListContract.Presenter createPresenter() {
        return new OrderListPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
    }


    private void initData() {
        mAdapter = new OrderListRVAdapter();
        mAdapter.setActivity((SupportActivity) _mActivity);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setAdapter(mAdapter);

        mParams.category = "123";
        mPresenter.getOrders(mParams);

    }

    private void initListener() {

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ((SupportActivity) _mActivity).start(OrderDetailFragment.newInstance());
            }
        });
    }

    @Override
    public void responseOrders(List<OrderBean> data) {
        mAdapter.setNewData(data);
    }
}
