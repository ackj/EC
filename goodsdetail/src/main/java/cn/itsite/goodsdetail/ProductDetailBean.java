package cn.itsite.goodsdetail;

import java.util.List;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/21 0021 17:44
 */

public class ProductDetailBean {


    /**
     * attributes : [{"attribute":"领券","name":"满140元减10元券","type":{"name":"积分","uid":"6564656565656"},"uid":"356465464"},{"attribute":"服务","name":"7天无理由","type":{"name":"服务类型","uid":"6564656565656"},"uid":"356465464"},{"attribute":"参数","name":"净含量12g，包装方式...","uid":"356465464"},{"attribute":"促销","name":"满1元可享受10倍积分","uid":"356465464"}]
     * description : 500g/包
     * detail : {"images":["http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg","http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg","http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg"],"url":"https://item.jd.com/4264502.html"}
     * images : [{"discription":"500g/包","image":"http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg"},{"discription":"500g/包","image":"http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg"},{"discription":"500g/包","image":"http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg"}]
     * pay : {"cost":"4.125","currency":"¥","discount":"7.5","price":"5.5"}
     * share : https://item.jd.com/4264502.html
     * shop : {"name":"克拉家园店","serviceType":"","type":"shop","uid":"54545454545"}
     * title : 巧克力豆
     * uid : 45645646545454
     */

    private String description;
    private DetailBean detail;
    private PayBean pay;
    private String share;
    private ShopBean shop;
    private String title;
    private String uid;
    private List<AttributesBean> attributes;
    private List<ImagesBean> images;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public PayBean getPay() {
        return pay;
    }

    public void setPay(PayBean pay) {
        this.pay = pay;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public ShopBean getShop() {
        return shop;
    }

    public void setShop(ShopBean shop) {
        this.shop = shop;
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

    public List<AttributesBean> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributesBean> attributes) {
        this.attributes = attributes;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class DetailBean {
        /**
         * images : ["http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg","http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg","http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg"]
         * url : https://item.jd.com/4264502.html
         */

        private String url;
        private List<String> images;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class PayBean {
        /**
         * cost : 4.125
         * currency : ¥
         * discount : 7.5
         * price : 5.5
         */

        private String cost;
        private String currency;
        private String discount;
        private String price;

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
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

    public static class AttributesBean {
        /**
         * attribute : 领券
         * name : 满140元减10元券
         * type : {"name":"积分","uid":"6564656565656"}
         * uid : 356465464
         */

        private String attribute;
        private String name;
        private TypeBean type;
        private String uid;

        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public TypeBean getType() {
            return type;
        }

        public void setType(TypeBean type) {
            this.type = type;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public static class TypeBean {
            /**
             * name : 积分
             * uid : 6564656565656
             */

            private String name;
            private String uid;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }
        }
    }

    public static class ImagesBean {
        /**
         * discription : 500g/包
         * image : http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg
         */

        private String discription;
        private String image;

        public String getDiscription() {
            return discription;
        }

        public void setDiscription(String discription) {
            this.discription = discription;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
