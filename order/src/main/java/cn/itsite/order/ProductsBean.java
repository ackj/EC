package cn.itsite.order;

public class ProductsBean {
    /**
     * imageUrl : http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg
     * detailUrl : https://item.jd.com/4264502.html
     * title : 优乐美奶茶
     * description : wifi/电话双网 您的智能小卫士
     * uid : 13212133313
     * type : smarthome
     * price : 5.0
     * currency : ¥
     */

    private String imageUrl;
    private String link;
    private String title;
    private String description;
    private String uid;
    private String type;
    private String price;
    private String currency;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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