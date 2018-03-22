package cn.itsite.classify;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
        ImageView ivIcon = helper.getView(R.id.iv_icon);
        Glide.with(ivIcon.getContext())
                .load(item.getImageUrl())
                .into(ivIcon);
        helper.setText(R.id.tv_name, item.getTitle())
                .setText(R.id.tv_desc, item.getDescription())
                .setText(R.id.tv_price, item.getCurrency() + item.getPrice());
    }
}
