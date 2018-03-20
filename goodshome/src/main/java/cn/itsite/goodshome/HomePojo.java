package cn.itsite.goodshome;

import java.util.List;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/20 0020 9:56
 */

public class HomePojo {

    private List<ProductsBean> ads;
    private List<RecommendationsBean> recommendations;

    public List<ProductsBean> getAds() {
        return ads;
    }

    public void setAds(List<ProductsBean> ads) {
        this.ads = ads;
    }

    public List<RecommendationsBean> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<RecommendationsBean> recommendations) {
        this.recommendations = recommendations;
    }

    public static class RecommendationsBean {
        /**
         * category : {"category":"智能主机","uid":"516165654656"}
         * products : [{"currency":"¥","description":"wifi/电话双网 您的智能小卫士","imageUrl":"http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg","link":"https://www.baidu.com/","price":"589.0","title":"安防小卫士","type":"product","uid":"13212133313"},{"currency":"¥","description":"wifi/电话双网 您的智能小卫士","imageUrl":"http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg","link":"https://www.baidu.com/","price":"589.0","title":"安防小卫士","type":"product","uid":"13212133313"},{"currency":"¥","description":"wifi/电话双网 您的智能小卫士","imageUrl":"http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg","link":"https://www.baidu.com/","price":"589.0","title":"安防小卫士","type":"product","uid":"13212133313"},{"currency":"¥","description":"wifi/电话双网 您的智能小卫士","imageUrl":"http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg","link":"https://www.baidu.com/","price":"589.0","title":"安防小卫士","type":"product","uid":"13212133313"},{"currency":"¥","description":"wifi/电话双网 您的智能小卫士","imageUrl":"http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg","link":"https://www.baidu.com/","price":"589.0","title":"安防小卫士","type":"product","uid":"13212133313"}]
         */

        private CategoryBean category;
        private List<ProductsBean> products;

        public CategoryBean getCategory() {
            return category;
        }

        public void setCategory(CategoryBean category) {
            this.category = category;
        }

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

    }
}


