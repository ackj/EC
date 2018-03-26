package cn.itsite.shoppingcart;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import cn.itsite.acommon.StorePojo;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/14 0014 9:57
 */

public class StoreBean implements MultiItemEntity {
    private static final String TAG = StorePojo.class.getSimpleName();

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

    private StorePojo.ShopBean shopBean;

    private StorePojo.ProductsBean productsBean;

    private RecommendGoodsBean recommendGoodsBean;

    public RecommendGoodsBean getRecommendGoodsBean() {
        return recommendGoodsBean;
    }

    public void setRecommendGoodsBean(RecommendGoodsBean recommendGoodsBean) {
        this.recommendGoodsBean = recommendGoodsBean;
    }

    public StorePojo.ShopBean getShopBean() {
        return shopBean;
    }

    public void setShopBean(StorePojo.ShopBean shopBean) {
        this.shopBean = shopBean;
    }

    public StorePojo.ProductsBean getProductsBean() {
        return productsBean;
    }

    public void setProductsBean(StorePojo.ProductsBean productsBean) {
        this.productsBean = productsBean;
    }
}
