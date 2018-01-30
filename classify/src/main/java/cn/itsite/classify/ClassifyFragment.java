package cn.itsite.classify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.BaseApplication;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.DensityUtils;
import cn.itsite.abase.utils.ScreenUtils;

/**
 * Author： Administrator on 2018/1/29 0029.
 * Email： liujia95me@126.com
 */

public class ClassifyFragment extends BaseFragment {

    public static final String TAG = ClassifyFragment.class.getSimpleName();

    private static final int SPAN_COUNT_ONE = 1;
    private static final int SPAN_COUNT_TWO = 2;

    private RecyclerView mRvMenu;
    private RecyclerView mRvContent;
    private RecyclerView mRvSubMenu;
    private LinearLayout mLlToolbar;
    private ImageView mIvSwitch;
    private ImageView mIvUnfold;

    private ClassifyMenuRVAdapter mAdapterMenu;
    private ClassifySubMenuRVAdapter mAdapterSubMenu;
    private ClassifyContentGridRVAdapter mAdapterContentGrid;
    private ClassifyContentLinearRVAdapter mAdapterContentLinear;
    private LinearLayout mLlUnfoldable;

    private GridLayoutManager mContentLayoutManager;

    private static final int ONE_UNFOLD_LINE_HEIGHT = DensityUtils.dp2px(BaseApplication.mContext,33);
    private int maxUnfoldHeight;//展开的最大高度，不能超过(ONE_UNFOLD_LINE_HEIGHT)的4倍高

    public static ClassifyFragment newInstance() {
        return new ClassifyFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classify, container, false);
        mLlToolbar = view.findViewById(R.id.ll_toolbar);
        mRvMenu = view.findViewById(R.id.rv_menu);
        mRvContent = view.findViewById(R.id.rv_content);
        mIvSwitch = view.findViewById(R.id.iv_switch);
        mRvSubMenu = view.findViewById(R.id.rv_sub_menu);
        mLlUnfoldable = view.findViewById(R.id.ll_unfoldable);
        mIvUnfold = view.findViewById(R.id.iv_unfold);
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
        mContentLayoutManager = new GridLayoutManager(_mActivity, SPAN_COUNT_TWO);

        mRvMenu.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapterMenu = new ClassifyMenuRVAdapter();
        mRvMenu.setAdapter(mAdapterMenu);

        mRvContent.setLayoutManager(mContentLayoutManager);
        mAdapterContentGrid = new ClassifyContentGridRVAdapter();
        mAdapterContentLinear = new ClassifyContentLinearRVAdapter();
        mRvContent.setAdapter(mAdapterContentGrid);

        mRvSubMenu.setLayoutManager(new GridLayoutManager(_mActivity, 3));
        mAdapterSubMenu = new ClassifySubMenuRVAdapter();
        mRvSubMenu.setAdapter(mAdapterSubMenu);


        List<String> data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        mAdapterMenu.setNewData(data);
        mAdapterSubMenu.setNewData(data);
        mAdapterContentGrid.setNewData(data);
        mAdapterContentLinear.setNewData(data);

    }

    private void initListener() {
        mIvSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContentLayoutManager.getSpanCount() == SPAN_COUNT_ONE) {
                    mContentLayoutManager.setSpanCount(SPAN_COUNT_TWO);
                    mRvContent.setAdapter(mAdapterContentGrid);
                } else {
                    mContentLayoutManager.setSpanCount(SPAN_COUNT_ONE);
                    mRvContent.setAdapter(mAdapterContentLinear);
                }
            }
        });

        mIvUnfold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mLlUnfoldable.getLayoutParams();
                layoutParams.height = DensityUtils.dp2px(_mActivity,60);
                mLlUnfoldable.setLayoutParams(layoutParams);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
