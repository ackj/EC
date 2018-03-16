package cn.itsite.order;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Author： Administrator on 2018/2/1 0001.
 * Email： liujia95me@126.com
 */

public class SubmitOrderBean  implements MultiItemEntity {
    private static final String TAG = SubmitOrderBean.class.getSimpleName();

    public static final int TYPE_STORE_TITLE = 1;
    public static final int TYPE_STORE_GOODS = 2;
    public static final int TYPE_ORDER_INFO = 3;

    private int itemType;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    private OrderBean orderInfoBean;
    private ProductsBean productsBean;

    public OrderBean getOrderInfoBean() {
        return orderInfoBean;
    }

    public void setOrderInfoBean(OrderBean orderInfoBean) {
        this.orderInfoBean = orderInfoBean;
    }

    public ProductsBean getProductsBean() {
        return productsBean;
    }

    public void setProductsBean(ProductsBean productsBean) {
        this.productsBean = productsBean;
    }
}
