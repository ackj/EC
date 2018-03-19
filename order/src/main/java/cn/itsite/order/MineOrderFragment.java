package cn.itsite.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.ScreenUtils;
import cn.itsite.order.contract.MineOrderContract;
import cn.itsite.order.presenter.MineOrderPresenter;

/**
 * Author： Administrator on 2018/2/1 0001.
 * Email： liujia95me@126.com
 */
@Route(path="/order/mineorderfragment")
public class MineOrderFragment extends BaseFragment<MineOrderContract.Presenter> implements MineOrderContract.View {

    public static final String TAG = MineOrderFragment.class.getSimpleName();

    private RelativeLayout mLlToolbar;
    private SubmitOrderRVAdapter mAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public static MineOrderFragment newInstance() {
        return new MineOrderFragment();
    }

    @NonNull
    @Override
    protected MineOrderContract.Presenter createPresenter() {
        return new MineOrderPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_order, container, false);
        mLlToolbar = view.findViewById(R.id.rl_toolbar);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
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
        mPresenter.getCategories("","orders");
    }

    private void initListener() {
    }

    @Override
    public void responseGetCategories(List<CategoryBean> data) {
        MineOrderVPAdapter mAdapter = new MineOrderVPAdapter(getChildFragmentManager(),data);
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
