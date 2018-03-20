package cn.itsite.order;

import java.util.List;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/15 0015 14:25
 */

public class OrderBean {


    /**
     * actions : [{"action":"取消订单","category":"2353552352"},{"action":"去付款","category":"2353343535"},{"action":"删除订单","category":""}]
     * amount : 4
     * category : 待付款
     * cost : 10
     * deliveryType : 送货上门
     * products : [{"description":"wifi/电话双网 您的智能小卫士","detailUrl":"https://item.jd.com/4264502.html","imageUrl":"http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg","title":"优乐美奶茶","uid":"13212133313"},{"description":"wifi/电话双网 您的智能小卫士","detailUrl":"https://item.jd.com/4264502.html","imageUrl":"http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg","title":"优乐美奶茶","uid":"13212133313"}]
     * shop : {"cartUid":"235353552352","name":"克拉家园店","type":"shop","uid":"54545454545"}
     * uid : 43433331313
     */

    private String amount;
    private String category;
    private String cost;
    private String deliveryType;
    private ShopBean shop;
    private String uid;
    private List<ActionsBean> actions;
    private List<ProductsBean> products;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public ShopBean getShop() {
        return shop;
    }

    public void setShop(ShopBean shop) {
        this.shop = shop;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<ActionsBean> getActions() {
        return actions;
    }

    public void setActions(List<ActionsBean> actions) {
        this.actions = actions;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public static class ShopBean {
        /**
         * cartUid : 235353552352
         * name : 克拉家园店
         * type : shop
         * uid : 54545454545
         */

        private String cartUid;
        private String name;
        private String type;
        private String uid;

        public String getCartUid() {
            return cartUid;
        }

        public void setCartUid(String cartUid) {
            this.cartUid = cartUid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }

    public static class ActionsBean {
        /**
         * action : 取消订单
         * category : 2353552352
         */
        private String type;
        private String action;
        private String category;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }

    public static class ProductsBean {
        /**
         * description : wifi/电话双网 您的智能小卫士
         * detailUrl : https://item.jd.com/4264502.html
         * imageUrl : http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg
         * title : 优乐美奶茶
         * uid : 13212133313
         */

        private String description;
        private String detailUrl;
        private String imageUrl;
        private String title;
        private String uid;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
