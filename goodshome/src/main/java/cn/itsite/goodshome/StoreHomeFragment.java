package cn.itsite.goodshome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.ScreenUtils;

/**
 * Author： Administrator on 2018/1/30 0030.
 * Email： liujia95me@126.com
 */

public class StoreHomeFragment extends BaseFragment {

    private static final String TAG = StoreHomeFragment.class.getSimpleName();

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LinearLayout mLlToolbar;


    public static StoreHomeFragment newInstance() {
        return new StoreHomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_home, container, false);
        mTabLayout = view.findViewById(R.id.tabLayout);
        mViewPager = view.findViewById(R.id.viewPager);
        mLlToolbar = view.findViewById(R.id.ll_toolbar);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initStatusBar();
        initData();
    }

    private void initStatusBar() {
        mLlToolbar.setPadding(mLlToolbar.getPaddingLeft(), mLlToolbar.getPaddingTop() + ScreenUtils.getStatusBarHeight(_mActivity), mLlToolbar.getPaddingRight(), mLlToolbar.getPaddingBottom());
    }

    private void initData() {
        StoreHomeVPAdapter mAdapter = new StoreHomeVPAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
