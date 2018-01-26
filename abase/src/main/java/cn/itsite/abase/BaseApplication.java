package cn.itsite.abase;

import android.content.Context;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import cn.itsite.abase.exception.AppExceptionHandler;
import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;


/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class BaseApplication extends MultiDexApplication {
    private static final String TAG = BaseApplication.class.getSimpleName();
    public static Context mContext;
    //    private RefWatcher mRefWatcher;
    public static String PUSH_TYPE = "";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initData();//根据是不是Debug版本来设置。
        initStrictMode();
    }

    private void initData() {

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag("SuiLiao")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });

        if (BuildConfig.DEBUG) {
            Fragmentation.builder()
                    // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出   NONE：隐藏
                    .stackViewMode(Fragmentation.BUBBLE)
                    // ture时，遇到异常："Can not perform this action after onSaveInstanceState!"时，会抛出
                    // false时，不会抛出，会捕获，可以在handleException()里监听到
                    .debug(BuildConfig.DEBUG)
                    // 在debug=false时，即线上环境时，上述异常会被捕获并回调ExceptionHandler
                    .handleException(new ExceptionHandler() {
                        @Override
                        public void onException(Exception e) {
                            // 建议在该回调处上传至我们的Crash监测服务器
                            // 以Bugtags为例子: 手动把捕获到的 Exception 传到 Bugtags 后台。
                            // Bugtags.sendException(e);
                        }
                    })
                    .install();

            //初始化内存泄露监听
//        mRefWatcher = LeakCanary.install(this);

            // 初始化卡顿监听
//        BlockCanary.install(this, new AppContext()).start();
        } else {
            Thread.setDefaultUncaughtExceptionHandler(AppExceptionHandler.getInstance(this));//在release版中处理全局异常。
        }
    }

    private void initStrictMode() {
// 分别为MainThread和VM设置Strict Mode
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
//                    .detectResourceMismatches()
                    .detectCustomSlowCalls()
                    .penaltyLog()
                    .build());

            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .detectLeakedRegistrationObjects()
                    .detectActivityLeaks()
                    .penaltyLog()
                    .build());
        }
    }
}
