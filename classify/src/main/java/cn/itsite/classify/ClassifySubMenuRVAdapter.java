package cn.itsite.classify;

import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.BaseApp;
import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author： Administrator on 2018/1/29 0029.
 * Email： liujia95me@126.com
 */

public class ClassifySubMenuRVAdapter extends BaseRecyclerViewAdapter<MenuBean.ChildrenBean, BaseViewHolder> {

    public ClassifySubMenuRVAdapter() {
        super(R.layout.item_classify_sub_menu);
    }

    private int selectedPosition = 0;

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, MenuBean.ChildrenBean item) {
        boolean isSelected = selectedPosition == helper.getLayoutPosition();
        helper.setText(R.id.tv_name, item.getCategory())
                .setTextColor(R.id.tv_name, isSelected ? BaseApp.mContext.getResources().getColor(R.color.base_color) : BaseApp.mContext.getResources().getColor(R.color.base_black));
    }
}
