package cn.itsite.order;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author： Administrator on 2018/2/1 0001.
 * Email： liujia95me@126.com
 */

public class SubmitOrderRVAdapter extends BaseMultiItemQuickAdapter<SubmitOrderBean, BaseViewHolder> {

    public SubmitOrderRVAdapter() {
        super(null);
        addItemType(SubmitOrderBean.TYPE_STORE_TITLE, R.layout.item_order_store_title);
        addItemType(SubmitOrderBean.TYPE_STORE_GOODS, R.layout.item_order_goods);
        addItemType(SubmitOrderBean.TYPE_ORDER_INFO, R.layout.item_order_info);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubmitOrderBean item) {
        switch (item.getItemType()) {
            case SubmitOrderBean.TYPE_STORE_TITLE:
                helper.setText(R.id.tv_name,item.getOrderInfoBean().getShop().getName());
                break;
            case SubmitOrderBean.TYPE_STORE_GOODS:
                ImageView ivIcon = helper.getView(R.id.iv_icon);
                Glide.with(ivIcon.getContext())
                        .load(item.getProductsBean().getImageUrl())
                        .into(ivIcon);
                helper.setText(R.id.tv_name,item.getProductsBean().getTitle())
                        .setText(R.id.tv_desc,item.getProductsBean().getDescription())
                        .setText(R.id.tv_price,item.getProductsBean().getCurrency()+" "+item.getProductsBean().getPrice());
                break;
            case SubmitOrderBean.TYPE_ORDER_INFO:
//                helper.setText(R.id.tv_amount,String.format("共%1$s件产品      合计：￥ %2$s",item.getOrderInfoBean().getAmount(),item.getOrderInfoBean().getCost()))
//                        .setText(R.id.tv_contactway,String.format("收货人：%1$s      %2$s",item.getOrderInfoBean().getName(),item.getOrderInfoBean().getPhoneNumber()))
//                        .setText(R.id.tv_location,item.getOrderInfoBean().getLocation()+item.getOrderInfoBean().getAddress())
//                        .setText(R.id.tv_leave_message,item.getOrderInfoBean().getNote());
                break;
            default:
        }
    }
}