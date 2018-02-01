package cn.itsite.order;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author： Administrator on 2018/2/1 0001.
 * Email： liujia95me@126.com
 */

public class SubmitOrderRVAdapter extends BaseMultiItemQuickAdapter<SubmitOrderBean,BaseViewHolder> {

    public SubmitOrderRVAdapter() {
        super(null);
        addItemType(SubmitOrderBean.TYPE_STORE_TITLE,R.layout.item_order_store_title);
        addItemType(SubmitOrderBean.TYPE_STORE_GOODS,R.layout.item_order_goods);
        addItemType(SubmitOrderBean.TYPE_ORDER_INFO,R.layout.item_order_info);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubmitOrderBean item) {

    }
}