package cn.itsite.classify;

import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author： Administrator on 2018/1/29 0029.
 * Email： liujia95me@126.com
 */

public class ClassifySubMenuRVAdapter extends BaseRecyclerViewAdapter<MenuBean.ChildrenBean,BaseViewHolder> {

    public ClassifySubMenuRVAdapter() {
        super(R.layout.item_classify_sub_menu);
    }

    @Override
    protected void convert(BaseViewHolder helper, MenuBean.ChildrenBean item) {
        helper.setText(R.id.tv_name,item.getCategory());
    }
}
