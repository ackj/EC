package cn.itsite.goodshome;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import cn.itsite.acommon.GoodsGridBean;

/**
 * Author： Administrator on 2018/1/30 0030.
 * Email： liujia95me@126.com
 */

public class StoreItemGridBean extends GoodsGridBean implements MultiItemEntity {

    public static final int TYPE_MORE = 1;
    public static final int TYPE_RECOMMEND = 2;

    private int itemType;
    private int spanSize;


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
}
