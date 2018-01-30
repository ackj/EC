package cn.itsite.abase.common;


import cn.itsite.abase.BaseApp;
import cn.itsite.abase.utils.DirectoryUtils;

import java.io.File;


/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class BaseConstants {
    private final String TAG = BaseConstants.class.getSimpleName();

    /**
     * 不允许new
     */
    protected BaseConstants() {
        throw new Error("Do not need instantiate!");
    }

    //SD卡路径
    public static final String PATH_DATA = DirectoryUtils.getDiskCacheDirectory(BaseApp.mContext, "data").getAbsolutePath();
    public static final String PATH_NET_CACHE = PATH_DATA + File.separator + "NetCache";
    public static final String PATH_APK_CACHE = PATH_DATA + File.separator + "ApkCache";
}
