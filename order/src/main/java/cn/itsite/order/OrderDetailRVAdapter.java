package cn.itsite.order;

import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author： Administrator on 2018/2/1 0001.
 * Email： liujia95me@126.com
 */

public class OrderDetailRVAdapter extends BaseRecyclerViewAdapter<String,BaseViewHolder> {

    public OrderDetailRVAdapter() {
        super(R.layout.item_order_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
