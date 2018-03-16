package cn.itsite.shoppingcart;

import java.util.List;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/14 0014 9:39
 */

public class StorePojo {


    /**
     * shop : {"name":"克拉家园店","serviceType":"","type":"shop","uid":"54545454545"}
     * products : [{"icon":"","specification":"","count":2,"title":"巧克力豆","uid":"45645646545454","description":"500g/包","share":"https://item.jd.com/4264502.html","pay":{"cost":"4.125","discount":"7.5","price":"5.5","currency":"¥"}}]
     */

    private ShopBean shop;
    private List<ProductsBean> products;

    public ShopBean getShop() {
        return shop;
    }

    public void setShop(ShopBean shop) {
        this.shop = shop;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public static class ShopBean {
        /**
         * name : 克拉家园店
         * serviceType :
         * type : shop
         * uid : 54545454545
         */

        private String name;
        private String serviceType;
        private String type;
        private String uid;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
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

    public static class ProductsBean {
        /**
         * icon :
         * specification :
         * count : 2
         * title : 巧克力豆
         * uid : 45645646545454
         * description : 500g/包
         * share : https://item.jd.com/4264502.html
         * pay : {"cost":"4.125","discount":"7.5","price":"5.5","currency":"¥"}
         */

        private String icon;
        private String specification;
        private int count;
        private String title;
        private String uid;
        private String description;
        private String share;
        private PayBean pay;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public PayBean getPay() {
            return pay;
        }

        public void setPay(PayBean pay) {
            this.pay = pay;
        }

        public static class PayBean {
            /**
             * cost : 4.125
             * discount : 7.5
             * price : 5.5
             * currency : ¥
             */

            private String cost;
            private String discount;
            private String price;
            private String currency;

            public String getCost() {
                return cost;
            }

            public void setCost(String cost) {
                this.cost = cost;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getCurrency() {
                return currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }
        }
    }
}
