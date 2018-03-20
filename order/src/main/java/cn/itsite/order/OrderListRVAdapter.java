package cn.itsite.order;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.BaseApp;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Author： Administrator on 2018/2/1 0001.
 * Email： liujia95me@126.com
 */

public class OrderListRVAdapter extends BaseRecyclerViewAdapter<OrderBean, BaseViewHolder> {

    public OrderListRVAdapter() {
        super(R.layout.item_order_list);
    }

    private SupportActivity activity;

    public void setActivity(SupportActivity activity){
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        helper.setText(R.id.tv_price, "￥ " + item.getCost())
                .setText(R.id.tv_name, item.getShop().getName())
                .setText(R.id.tv_category, item.getCategory())
                .setTextColor(R.id.tv_delivery_type, isDeliveryToDoor(item) ?
                        BaseApp.mContext.getResources().getColor(R.color.base_color) :
                        BaseApp.mContext.getResources().getColor(R.color.green))
                .setText(R.id.tv_delivery_type, item.getDeliveryType())
                .setBackgroundRes(R.id.tv_delivery_type, isDeliveryToDoor(item) ?
                        R.drawable.shape_bg_round_orange : R.drawable.shape_bg_round_green);
        recyclerView.setLayoutManager(new LinearLayoutManager(BaseApp.mContext, LinearLayoutManager.HORIZONTAL, false));
        OrderItemImageAdapter adapter = new OrderItemImageAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setNewData(item.getProducts());
        Button btn1 = helper.getView(R.id.btn_1);
        Button btn2 = helper.getView(R.id.btn_2);
        btn1.setVisibility(View.INVISIBLE);
        btn2.setVisibility(View.INVISIBLE);
        for (int i = 0; i < item.getActions().size(); i++) {
            if (i == 0) {
                btn2.setText(item.getActions().get(i).getAction());
                btn2.setVisibility(View.VISIBLE);
            } else if (i == 1) {
                btn1.setText(item.getActions().get(i).getAction());
                btn1.setVisibility(View.VISIBLE);
            }
        }

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Fragment fragment = (Fragment) ARouter.getInstance().build("/goodsdetail/goodsdetailfragment").navigation();
                activity.start((BaseFragment) fragment);
            }
        });
    }

    private boolean isDeliveryToDoor(OrderBean item) {
        return item.getDeliveryType().contains("上门");
    }

    class OrderItemImageAdapter extends BaseRecyclerViewAdapter<OrderBean.ProductsBean, BaseViewHolder> {

        public OrderItemImageAdapter() {
            super(R.layout.item_item_goods_image);
        }

        @Override
        protected void convert(BaseViewHolder helper, OrderBean.ProductsBean item) {
            ImageView ivImg = helper.getView(R.id.iv_img);
            Glide.with(ivImg.getContext())
                    .load(item.getImageUrl())
                    .into(ivImg);
        }
    }
}