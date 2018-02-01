package cn.itsite.goodssearch;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author： Administrator on 2018/1/31 0031.
 * Email： liujia95me@126.com
 */

public class SearchGoodsRVAdapter extends BaseMultiItemQuickAdapter<SearchGoodsBean,BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     */
    public SearchGoodsRVAdapter() {
        super(null);
        addItemType(SearchGoodsBean.TYPE_HISTORY_TITLE,R.layout.item_search_history_title);
        addItemType(SearchGoodsBean.TYPE_HISTORY_ITEM,R.layout.item_search_history_goods);
        addItemType(SearchGoodsBean.TYPE_SEARCH_STRING,R.layout.item_search_string);
        addItemType(SearchGoodsBean.TYPE_SEARCH_GOODS,R.layout.item_grid_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchGoodsBean item) {

    }
}
