package cn.itsite.classify;

import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author： Administrator on 2018/1/29 0029.
 * Email： liujia95me@126.com
 */

public class ClassifyMenuRVAdapter extends BaseRecyclerViewAdapter<String,BaseViewHolder> {

    public ClassifyMenuRVAdapter() {
        super(R.layout.item_classify_menu);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
