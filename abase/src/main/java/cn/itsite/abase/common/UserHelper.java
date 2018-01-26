package cn.itsite.abase.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import cn.itsite.abase.BaseApplication;

/**
 * Author：leguang on 2016/5/4 0009 15:49
 * Email：langmanleguang@qq.com
 */

public class UserHelper {
    public static final String TAG = UserHelper.class.getSimpleName();
    public static final String FILE_NAME = "default";
    public static final String TOKEN = "token";
    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "password";
    public static final String USER_INFO = "user_info";
    public static final String ISREMEMBER = "isRemember";
    public static final String DEVICE_ID = "device_id";

    public static String account = "";//账户、密码、是否记住密码，这三个值是记录在默认SP中的。
    public static String password = "";//同时账户和密码在各自的SP中也有一份。
    public static String token = "";
    public static String deviceID = "";

    //判断是否登录。
    public static boolean isLogined() {
        return !TextUtils.isEmpty(token);
    }

    //退出登录或者token失效清除信息。
    public static void clear() {
        token = "";
        account = "";
        password = "";
    }

    //更新Token。
    public static boolean setToken(String token) {
        UserHelper.token = token;
        SharedPreferences.Editor editor = getEditor();
        editor.putString(TOKEN, token);
        return editor.commit();
    }

    public static void setAccount(String account, String password) {
        UserHelper.account = account;
        UserHelper.password = password;
        getEditor()
                .putString(ACCOUNT, account)
                .putString(PASSWORD, password)
                .commit();
    }

    public static String getDeviceID() {
        return getSp().getString(DEVICE_ID, "");
    }

    public static String getAccount() {
        return getSp().getString(ACCOUNT, "");
    }

    public static String getPassword() {
        return getSp().getString(PASSWORD, "");
    }

    //用户数据的初始化入口。
    public static void init() {
        initData();
    }

    private static void initData() {
        SharedPreferences sp = getSp();
        token = sp.getString(TOKEN, "");
        account = sp.getString(ACCOUNT, "");
        password = sp.getString(PASSWORD, "");
        deviceID = sp.getString(DEVICE_ID, "");
    }

    //更新是否记住密码。
    public static boolean setRemember(boolean isRemember) {
        return getEditor().putBoolean(ISREMEMBER, isRemember).commit();
    }

    public static boolean isRemember() {
        return getSp().getBoolean(ISREMEMBER, false);
    }

    private static SharedPreferences getSp() {
        return BaseApplication.mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor() {
        return getSp().edit();
    }
}
