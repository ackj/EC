package cn.itsite.acommon;

import java.util.List;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/22 0022 14:26
 */

public class SkusBean {


    private List<AttributesBean> attributes;
    private List<SkuBean> skus;

    public List<AttributesBean> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributesBean> attributes) {
        this.attributes = attributes;
    }

    public List<SkuBean> getSkus() {
        return skus;
    }

    public void setSkus(List<SkuBean> skus) {
        this.skus = skus;
    }

    public static class AttributesBean {
        /**
         * attribute : 性别
         * values : [{"stockQuantity":30,"value":"男","skus":["134343243244","134343243244"]},{"stockQuantity":10,"value":"女","skus":["134343243244","134343243244"]}]
         */

        private String attribute;
        private List<ValuesBean> values;


        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }

        public List<ValuesBean> getValues() {
            return values;
        }

        public void setValues(List<ValuesBean> values) {
            this.values = values;
        }

        public static class ValuesBean {
            /**
             * stockQuantity : 30
             * value : 男
             * skus : ["134343243244","134343243244"]
             */

            private int stockQuantity;
            private String value;
            private List<String> skus;
            private boolean hasIntersection; //自己加的，与后台数据无关
            private boolean selected;//是否选中

            public boolean isSelected() {
                return selected;
            }

            public void setSelected(boolean selected) {
                this.selected = selected;
            }

            public boolean isHasIntersection() {
                return hasIntersection;
            }

            public void setHasIntersection(boolean hasIntersection) {
                this.hasIntersection = hasIntersection;
            }
            public int getStockQuantity() {
                return stockQuantity;
            }

            public void setStockQuantity(int stockQuantity) {
                this.stockQuantity = stockQuantity;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public List<String> getSkus() {
                return skus;
            }

            public void setSkus(List<String> skus) {
                this.skus = skus;
            }
        }
    }

    public static class SkuBean {
        /**
         * sku : 男+红色+X码
         * stockQuantity : 30
         * price : 50
         * currency : ¥
         * note : 备注：该商品XXX
         * uid : 34646464464
         * imageUrl : http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg
         */

        private String sku;
        private int stockQuantity;
        private int price;
        private String currency;
        private String note;
        private String uid;
        private String imageUrl;

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public int getStockQuantity() {
            return stockQuantity;
        }

        public void setStockQuantity(int stockQuantity) {
            this.stockQuantity = stockQuantity;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
