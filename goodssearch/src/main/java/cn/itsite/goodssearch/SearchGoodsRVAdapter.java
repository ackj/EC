package cn.itsite.goodssearch;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author： Administrator on 2018/1/31 0031.
 * Email： liujia95me@126.com
 */

public class SearchGoodsRVAdapter extends BaseMultiItemQuickAdapter<SearchGoodsBean, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     */
    public SearchGoodsRVAdapter() {
        super(null);
        addItemType(SearchGoodsBean.TYPE_HISTORY_TITLE, R.layout.item_search_history_title);
        addItemType(SearchGoodsBean.TYPE_HISTORY_ITEM, R.layout.item_search_history_goods);
        addItemType(SearchGoodsBean.TYPE_SEARCH_STRING, R.layout.item_search_string);
        addItemType(SearchGoodsBean.TYPE_SEARCH_GOODS, R.layout.item_grid_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchGoodsBean item) {
        switch (item.getItemType()) {
            case SearchGoodsBean.TYPE_HISTORY_TITLE:
                helper.setText(R.id.tv_title, item.getTitle())
                        .setVisible(R.id.iv_clear, item.getTitle().contains("历史"));
                break;
            case SearchGoodsBean.TYPE_HISTORY_ITEM:
                helper.setText(R.id.tv_keyword, item.getKeywordBean().getQuery());
                break;
            case SearchGoodsBean.TYPE_SEARCH_STRING:
                break;
            case SearchGoodsBean.TYPE_SEARCH_GOODS:
                ImageView mIvIcon = helper.getView(R.id.iv_icon);
                Glide.with(mIvIcon.getContext())
                        .load(item.getGoodsBean().getImageUrl())
                        .into(mIvIcon);
                helper.addOnClickListener(R.id.cl_goods_layout)
                        .setText(R.id.tv_name, item.getGoodsBean().getTitle())
                        .setText(R.id.tv_desc, item.getGoodsBean().getDescription())
                        .setText(R.id.tv_price, item.getGoodsBean().getCurrency() + " " + item.getGoodsBean().getPrice());
                break;
            default:
        }
    }
}
