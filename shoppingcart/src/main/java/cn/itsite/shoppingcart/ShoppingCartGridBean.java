package cn.itsite.shoppingcart;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Author： Administrator on 2018/1/31 0031.
 * Email： liujia95me@126.com
 */

public class ShoppingCartGridBean implements MultiItemEntity {
    private static final String TAG = ShoppingCartGridBean.class.getSimpleName();

    public static final int TYPE_STORE_TITLE = 1;
    public static final int TYPE_STORE_GOODS = 2;
    public static final int TYPE_RECOMMEND_TITLE = 3;
    public static final int TYPE_RECOMMEND_GOODS = 4;

    private boolean isChecked;


    //关于局部刷新的实现：商店存储商品的个数，然后只需刷新adapter.range(position,商品个数)即可
    private int goodsCount;

    private int itemType;
    private int spanSize;

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

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
