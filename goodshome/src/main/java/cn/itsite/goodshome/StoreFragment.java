package cn.itsite.goodshome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.acommon.Params;
import cn.itsite.goodshome.contract.HomeContract;
import cn.itsite.goodshome.presenter.HomePresenter;
import cn.itsite.statemanager.BaseViewHolder;
import cn.itsite.statemanager.StateLayout;
import cn.itsite.statemanager.StateListener;
import cn.itsite.statemanager.StateManager;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author： Administrator on 2018/1/30 0030.
 * Email： liujia95me@126.com
 */

public class StoreFragment extends BaseFragment<HomeContract.Presenter> implements HomeContract.View {

    private static final String TAG = StoreFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private StoreRVAdapter mAdatper;
    private LinearLayout mLlLocation;
    private List<StoreItemGridBean> mDatas;
    private Banner mBanner;
    private List<Object> mBannerImages;
    private List<String> mBannerTitles;
    private Params mParmas = new Params();
    private StateManager mStateManager;
    private PtrFrameLayout mPtrFrameLayout;

    public static StoreFragment newInstance() {
        return new StoreFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    protected HomeContract.Presenter createPresenter() {
        return new HomePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mPtrFrameLayout = view.findViewById(R.id.ptrFrameLayout);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initStateManager();
        initData();
        initListener();
        initPtrFrameLayout(mPtrFrameLayout, mRecyclerView);
    }

    private void initData() {
        //添加Banner头
        mAdatper = new StoreRVAdapter();
        mRecyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 2));
        mRecyclerView.setAdapter(mAdatper);

        mDatas = new ArrayList<>();
        mAdatper.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return mDatas.get(position).getSpanSize();
            }
        });

        mParmas.type = "shop";
        mPresenter.getHome(mParmas);
    }

    private void initStateManager() {
        mStateManager = StateManager.builder(_mActivity)
                .setContent(mRecyclerView)
                .setEmptyView(R.layout.state_layout)
                .setEmptyImage(R.drawable.ic_prompt_dingwei_01)
                .setErrorOnClickListener(v -> mPtrFrameLayout.autoRefresh())
                .setConvertListener(new StateListener.ConvertListener() {
                    @Override
                    public void convert(BaseViewHolder holder, StateLayout stateLayout) {
                        holder.setText(R.id.bt_empty_state, "切换地址");
                    }
                })
                .setEmptyText("当前暂无商品，请切换地址试试吧！")
                .build();
    }

    private void initListener() {
        mAdatper.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StoreItemGridBean item = mAdatper.getItem(position);
                switch (item.getItemType()) {
                    case StoreItemGridBean.TYPE_BANNER:
                        if (view.getId() == R.id.ll_location) {
                            Fragment addressFragment = (Fragment) ARouter.getInstance().build("/delivery/selectshoppingaddressfragment").navigation();
                            ((StoreHomeFragment) getParentFragment()).start((BaseFragment) addressFragment);
                        }
                        break;
                    case StoreItemGridBean.TYPE_MORE:
                        Fragment fragment = (Fragment) ARouter.getInstance().build("/classify/classifyfragment").navigation();
                        ((StoreHomeFragment) getParentFragment()).start((BaseFragment) fragment);
                        break;
                    case StoreItemGridBean.TYPE_RECOMMEND:
                    case StoreItemGridBean.TYPE_GOODS:
                        Fragment goodsDetailFragment = (Fragment) ARouter.getInstance().build("/goodsdetail/goodsdetailfragment").navigation();
                        ((StoreHomeFragment) getParentFragment()).start((BaseFragment) goodsDetailFragment);
                        break;
                    default:
                }
            }
        });
    }

    @Override
    public void responseGetHome(List<StoreItemGridBean> data) {
        mPtrFrameLayout.refreshComplete();
        if (data == null || data.isEmpty()) {
            mStateManager.showEmpty();
        } else {
            mStateManager.showContent();
        }
        mDatas = data;
        mAdatper.setNewData(mDatas);
    }
}
