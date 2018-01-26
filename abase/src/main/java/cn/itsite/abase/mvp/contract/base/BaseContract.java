package cn.itsite.abase.mvp.contract.base;

import android.support.annotation.UiThread;

/**
 * Author：leguang on 2016/10/10 0010 20:44
 * Email：langmanleguang@qq.com
 * <p>
 * 所有契约接口的基类接口，定义了各层对象的生命周期。
 */
public interface BaseContract {
    interface View {

        void start(Object response);

        void error(String errorMessage);

        void complete(Object response);
    }

    interface Presenter {

        /**
         * Presenter的生命周期开始。
         */
        void start(Object request);

        /**
         * Presenter的生命周期结束，释放资源。
         */
        @UiThread
        void clear();
    }

    interface Model {

        /**
         * Model的生命周期开始。
         */
        void start(Object request);

        /**
         * Model的生命周期结束，释放资源。
         */
        void clear();
    }
}
