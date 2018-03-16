package cn.itsite.goodssearch;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Author： Administrator on 2018/1/30 0030.
 * Email： liujia95me@126.com
 */

public class SearchGoodsBean implements MultiItemEntity {

    private static final String TAG = SearchGoodsBean.class.getSimpleName();

    public static final int TYPE_HISTORY_TITLE = 1;
    public static final int TYPE_HISTORY_ITEM = 2;
    public static final int TYPE_SEARCH_STRING = 3;
    public static final int TYPE_SEARCH_GOODS = 4;

    private int itemType;
    private int spanSize;

    private String title;
    private KeywordBean keywordBean;
    private GoodsBean goodsBean;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public int getSpanSize() {
        return spanSize;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public KeywordBean getKeywordBean() {
        return keywordBean;
    }

    public void setKeywordBean(KeywordBean keywordBean) {
        this.keywordBean = keywordBean;
    }

    public GoodsBean getGoodsBean() {
        return goodsBean;
    }

    public void setGoodsBean(GoodsBean goodsBean) {
        this.goodsBean = goodsBean;
    }
}
