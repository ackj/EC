package cn.itsite.delivery;

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
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.ScreenUtils;
import cn.itsite.delivery.contract.DeliveryContract;
import cn.itsite.delivery.presenter.DeliveryPresenter;

/**
 * Author： Administrator on 2018/1/31 0031.
 * Email： liujia95me@126.com
 */
@Route(path="/delivery/selectshoppingaddressfragment")
public class SelectShoppingAddressFragment extends BaseFragment<DeliveryContract.Presenter> implements DeliveryContract.View {

    private static final String TAG = SelectShoppingAddressFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RelativeLayout mRlToolbar;
    private TextView mTvAdd;

    private DeliveryRVAdapter mAdapter;

    public static SelectShoppingAddressFragment newInstance() {
        return new SelectShoppingAddressFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    protected DeliveryContract.Presenter createPresenter() {
        return new DeliveryPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_shopping_address, container, false);
        mRlToolbar = view.findViewById(R.id.rl_toolbar);
        mTvAdd = view.findViewById(R.id.tv_add);
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new DeliveryRVAdapter();
        View header = LayoutInflater.from(_mActivity).inflate(R.layout.item_nowaday_location, null);
        mAdapter.addHeaderView(header);
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.getAddress();

    }

    private void initListener() {
        mTvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(AddDeliveryFragment.newInstance());
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.iv_edit){
                    start(AddDeliveryFragment.newInstance());
                }
            }
        });
    }

    @Override
    public void responseGetAddress(List<DeliveryBean> data) {
        mAdapter.setNewData(data);
    }

    @Override
    public void responseDeleteAddressSuccess() {
        DialogHelper.successSnackbar(getView(),"删除成功");
    }
}
