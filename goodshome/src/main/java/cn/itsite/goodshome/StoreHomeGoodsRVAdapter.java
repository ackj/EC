package cn.itsite.goodshome;

import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author： Administrator on 2018/1/30 0030.
 * Email： liujia95me@126.com
 */

public class StoreHomeGoodsRVAdapter extends BaseRecyclerViewAdapter<String, BaseViewHolder> {
    public StoreHomeGoodsRVAdapter() {
        super(R.layout.item_grid_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}