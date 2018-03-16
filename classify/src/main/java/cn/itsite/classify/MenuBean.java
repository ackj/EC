package cn.itsite.classify;

import java.util.List;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/16 0016 10:17
 */

public class MenuBean {

    /**
     * parentUid :
     * category : 智能主机
     * uid : 123
     * children : [{"category":"智能门锁","uid":"77273681230"},{"category":"智能摄像","uid":"77273681230"}]
     */

    private String parentUid;
    private String category;
    private String uid;
    private List<ChildrenBean> children;

    public String getParentUid() {
        return parentUid;
    }

    public void setParentUid(String parentUid) {
        this.parentUid = parentUid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean {
        /**
         * category : 智能门锁
         * uid : 77273681230
         */

        private String category;
        private String uid;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
