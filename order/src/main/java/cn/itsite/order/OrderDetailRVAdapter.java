package cn.itsite.order;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author： Administrator on 2018/2/1 0001.
 * Email： liujia95me@126.com
 */

public class OrderDetailRVAdapter extends BaseRecyclerViewAdapter<OrderDetailBean.ProductsBean,BaseViewHolder> {

    public OrderDetailRVAdapter() {
        super(R.layout.item_order_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderDetailBean.ProductsBean item) {
        ImageView mIvIcon = helper.getView(R.id.iv_icon);
        Glide.with(mIvIcon.getContext())
                .load(item.getImageUrl())
                .into(mIvIcon);
        helper.addOnClickListener(R.id.cl_goods_layout)
                .setText(R.id.tv_amount,"x"+item.getAmount())
                .setText(R.id.tv_name, item.getTitle())
                .setText(R.id.tv_desc, item.getDescription())
                .setText(R.id.tv_price, item.getPay().getCurrency() + " " + item.getPay().getPrice());

    }
}
