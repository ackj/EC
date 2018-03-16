package cn.itsite.classify;

import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author： Administrator on 2018/1/29 0029.
 * Email： liujia95me@126.com
 */

public class ClassifyContentLinearRVAdapter extends BaseRecyclerViewAdapter<ProductBean, BaseViewHolder> {

    public ClassifyContentLinearRVAdapter() {
        super(R.layout.item_linear_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductBean item) {

    }
}
