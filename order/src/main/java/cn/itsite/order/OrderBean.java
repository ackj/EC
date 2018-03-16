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
     * shopName : 克拉家园便利店
     * shopUID : 656461778373
     * shopType : shop
     * amount : 2
     * state : 0
     * cost : 10
     * name : 黄沙
     * gender : male
     * note : 带一个勺子
     * phoneNumber :  13888888888
     * location : 凯宾斯基
     * address : C栋801
     * longitude :  85.66
     * latitude : 36.33
     * products : [{"imageUrl":"http://ww3.sinaimg.cn/large/0060lm7Tly1fo6vt0p500j30af0ad758.jpg","detailUrl":"https://item.jd.com/4264502.html","title":"优乐美奶茶","description":"wifi/电话双网 您的智能小卫士","uid":"13212133313","type":"smarthome","price":"5.0","currency":"¥"}]
     */

    private String shopName;
    private String shopUID;
    private String shopType;
    private String amount;
    private String state;
    private String cost;
    private String name;
    private String gender;
    private String note;
    private String phoneNumber;
    private String location;
    private String address;
    private String longitude;
    private String latitude;
    private List<ProductsBean> products;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopUID() {
        return shopUID;
    }

    public void setShopUID(String shopUID) {
        this.shopUID = shopUID;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }
}
