package cn.itsite.order;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.BaseApp;
import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author： Administrator on 2018/2/1 0001.
 * Email： liujia95me@126.com
 */

public class OrderListAdapter extends BaseRecyclerViewAdapter<OrderBean,BaseViewHolder>{

    public OrderListAdapter() {
        super(R.layout.item_order_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(BaseApp.mContext,LinearLayoutManager.HORIZONTAL,false));
        OrderItemImageAdapter adapter = new OrderItemImageAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setNewData(item.getProducts());
    }

    class OrderItemImageAdapter extends BaseRecyclerViewAdapter<OrderBean.ProductsBean,BaseViewHolder>{

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