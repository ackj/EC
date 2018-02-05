package cn.itsite.goodshome;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.DensityUtils;

/**
 * Author： Administrator on 2018/1/30 0030.
 * Email： liujia95me@126.com
 */

public class StoreFragment extends BaseFragment {

    private static final String TAG = StoreFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private StoreRVAdapter mAdatper;

    public static StoreFragment newInstance() {
        return new StoreFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        //添加Banner头
        mAdatper = new StoreRVAdapter();
        List<Integer> images = new ArrayList<>();
        List<String> bannerDatas = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            images.add(R.drawable.test);
            bannerDatas.add("一二三四五，上山打老虎");
        }
        Banner banner = (Banner) LayoutInflater.from(_mActivity).inflate(R.layout.item_store_banner,null);
        banner.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(_mActivity, 150)));
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        })
                .setBannerTitles(bannerDatas)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImages(images)
                .isAutoPlay(true)
                .start();
        mAdatper.addHeaderView(banner);

        mRecyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 2));
        mRecyclerView.setAdapter(mAdatper);

        //todo:待删
        final List<StoreItemBean> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            StoreItemBean moreBean = new StoreItemBean();
            moreBean.setItemType(StoreItemBean.TYPE_MORE);
            moreBean.setSpanSize(2);
            data.add(moreBean);

            StoreItemBean recommendBean = new StoreItemBean();
            recommendBean.setItemType(StoreItemBean.TYPE_RECOMMEND);
            recommendBean.setSpanSize(2);
            data.add(recommendBean);

            for (int j = 0; j < 4; j++) {
                StoreItemBean goodsBean = new StoreItemBean();
                goodsBean.setItemType(StoreItemBean.TYPE_GOODS);
                goodsBean.setSpanSize(1);
                data.add(goodsBean);
            }
        }

        mAdatper.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return data.get(position).getSpanSize();
            }
        });
        mAdatper.setNewData(data);
    }

    private void initListener() {
        mAdatper.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StoreItemBean item = mAdatper.getItem(position);
                switch (item.getItemType()) {
                    case StoreItemBean.TYPE_MORE:
                        break;
                    case StoreItemBean.TYPE_RECOMMEND:
                        break;
                    case StoreItemBean.TYPE_GOODS:
                        break;
                    default:
                }
            }
        });
    }

}
