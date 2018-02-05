package cn.itsite.delivery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.ScreenUtils;

/**
 * Author： Administrator on 2018/1/31 0031.
 * Email： liujia95me@126.com
 */

public class SelectShoppingAddressFragment extends BaseFragment {

    private static final String TAG = SelectShoppingAddressFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private LinearLayout mLlToolbar;
    private TextView mTvAdd;

    private AddressRVAdapter mAdapter;

    public static SelectShoppingAddressFragment newInstance() {
        return new SelectShoppingAddressFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_shopping_address, container, false);
        mLlToolbar = view.findViewById(R.id.ll_toolbar);
        mTvAdd = view.findViewById(R.id.tv_add);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        return view;
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
        mAdapter = new AddressRVAdapter();
        View header = LayoutInflater.from(_mActivity).inflate(R.layout.item_nowaday_location, null);
        mAdapter.addHeaderView(header);
        mRecyclerView.setAdapter(mAdapter);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("");
        }
        mAdapter.setNewData(data);
    }

    private void initListener() {
        mTvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(AddAddressFragment.newInstance());
            }
        });
    }

}
