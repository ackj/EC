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

import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.order.contract.OrderListContract;
import cn.itsite.order.presenter.OrderListPresenter;

/**
 * Author： Administrator on 2018/2/1 0001.
 * Email： liujia95me@126.com
 */
@Route(path="/order/orderlistfragment")
public class OrderListFragment extends BaseFragment<OrderListContract.Presenter> implements OrderListContract.View {

    public static final String TAG = OrderListFragment.class.getSimpleName();

    RecyclerView mRecyclerView;
    private OrderListAdapter mAdapter;

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
        mAdapter = new OrderListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.getOrders("123");

//        List<String> data = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            data.add("");
//        }
//        mAdapter.setNewData(data);
    }

    private void initListener() {
    }

    @Override
    public void responseOrders(List<OrderBean> data) {
        mAdapter.setNewData(data);
    }
}
