package cn.itsite.shoppingcart;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Author： Administrator on 2018/1/31 0031.
 * Email： liujia95me@126.com
 */

public class ShoppingCartBean implements MultiItemEntity {
    private static final String TAG = ShoppingCartBean.class.getSimpleName();

    public static final int TYPE_STORE_TITLE = 1;
    public static final int TYPE_STORE_GOODS = 2;
    public static final int TYPE_RECOMMEND_TITLE = 3;
    public static final int TYPE_RECOMMEND_GOODS = 4;

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
