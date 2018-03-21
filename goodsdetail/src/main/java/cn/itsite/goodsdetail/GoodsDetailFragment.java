package cn.itsite.goodsdetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tmall.ultraviewpager.UltraViewPager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import cn.itsite.abase.BaseApp;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.ScreenUtils;
import cn.itsite.acommon.SpecificationDialog;

/**
 * Author： Administrator on 2018/2/6 0006.
 * Email： liujia95me@126.com
 */
@Route(path = "/goodsdetail/goodsdetailfragment")
public class GoodsDetailFragment extends BaseFragment {

    public static final String TAG = GoodsDetailFragment.class.getSimpleName();

    private RelativeLayout mRlToolbar;
    private TextView mTvPutShopcart;
    private TextView mTvBuyItNow;
    private LinearLayout mLlShopCart;
    private UltraViewPager mUltraViewPager;
    private MagicIndicator mMagicIndicator;

    public static GoodsDetailFragment newInstance() {
        return new GoodsDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_detail, container, false);
        mRlToolbar = view.findViewById(R.id.rl_toolbar);
        mTvPutShopcart = view.findViewById(R.id.tv_put_shopcart);
        mTvBuyItNow = view.findViewById(R.id.tv_buy_it_now);
        mLlShopCart = view.findViewById(R.id.ll_shopcart);
        mUltraViewPager = view.findViewById(R.id.ultra_viewpager);
        mMagicIndicator = view.findViewById(R.id.magicIndicator);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initStatusBar();
        initData();
        initMagicIndicator();
        initListener();
    }

    private void initStatusBar() {
        mRlToolbar.setPadding(mRlToolbar.getPaddingLeft(), mRlToolbar.getPaddingTop() + ScreenUtils.getStatusBarHeight(_mActivity), mRlToolbar.getPaddingRight(), mRlToolbar.getPaddingBottom());
    }

    private void initData() {
        mUltraViewPager.setScrollMode(UltraViewPager.ScrollMode.VERTICAL);
        GoodsDetailVPAdapter adapter = new GoodsDetailVPAdapter(getChildFragmentManager());
        mUltraViewPager.setAdapter(adapter);
    }

    private void initListener() {
        mTvPutShopcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSpecificationDialog();
            }
        });
        mTvBuyItNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = (Fragment) ARouter.getInstance().build("/order/submitorderfragment").navigation();
                start((BaseFragment) fragment);
            }
        });
        mLlShopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = (Fragment) ARouter.getInstance().build("/shoppingcart/shoppingcartfragment").navigation();
                start((BaseFragment) fragment);
            }
        });

    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(_mActivity);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            private String[] mTitles = BaseApp.mContext.getResources().getStringArray(R.array.goods_detail_tabs);

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(mTitles[index]);
                simplePagerTitleView.setNormalColor(_mActivity.getResources().getColor(R.color.base_black));
                simplePagerTitleView.setSelectedColor(_mActivity.getResources().getColor(R.color.base_color));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mUltraViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 3));
                indicator.setLineWidth(UIUtil.dip2px(context, 28));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setYOffset(UIUtil.dip2px(context, 4));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(_mActivity.getResources().getColor(R.color.base_color));
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.simple_splitter_2));
        ViewPagerHelper.bind(mMagicIndicator, mUltraViewPager.getViewPager());
    }

    private void showSpecificationDialog() {
        SpecificationDialog dialog = new SpecificationDialog();
        dialog.show(getChildFragmentManager());
    }

}
