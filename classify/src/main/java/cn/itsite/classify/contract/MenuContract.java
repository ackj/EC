package cn.itsite.classify.contract;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.classify.MenuBean;
import cn.itsite.classify.ProductBean;
import rx.Observable;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/16 0016 10:20
 */

public interface MenuContract {
    interface View extends BaseContract.View {
        void responseGetGategories(List<MenuBean> data);
        void responseGetProducts(List<ProductBean> data);
    }

    interface Presenter extends BaseContract.Presenter {
        void getGategories(String uid);
        void getProducts(String uid);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseResponse<List<MenuBean>>> getGategories(String uid);
        Observable<BaseResponse<List<ProductBean>>> getProducts(String uid);
    }
}
