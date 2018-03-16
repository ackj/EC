package cn.itsite.delivery;

import java.util.List;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/15 0015 9:50
 */

public class RequestBean {


    /**
     * data : [{"address":"string","deafult":true,"gender":"string","latitude":"string","location":"string","longitude":"string","name":"string","phoneNumber":"string"}]
     * message : string
     */

    private String message;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address : string
         * deafult : true
         * gender : string
         * latitude : string
         * location : string
         * longitude : string
         * name : string
         * phoneNumber : string
         */

        private String address;
        private boolean deafult;
        private String gender;
        private String latitude;
        private String location;
        private String longitude;
        private String name;
        private String phoneNumber;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public boolean isDeafult() {
            return deafult;
        }

        public void setDeafult(boolean deafult) {
            this.deafult = deafult;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }
}
