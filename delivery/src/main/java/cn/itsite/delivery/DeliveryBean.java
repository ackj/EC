package cn.itsite.delivery;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/14 0014 15:42
 */

public class DeliveryBean {

    /**
     * uid : 123123123
     * name : BeJson
     * gender : male
     * phoneNumber : 13888888888
     * location : 凯宾斯基
     * address : C栋801
     * longitude : 85
     * latitude : 36
     * isDeafult : true
     */

    private String uid;
    private String name;
    private String gender;
    private String phoneNumber;
    private String location;
    private String address;
    private String longitude;
    private String latitude;
    private boolean isDeafult;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public boolean isIsDeafult() {
        return isDeafult;
    }

    public void setIsDeafult(boolean isDeafult) {
        this.isDeafult = isDeafult;
    }
}
