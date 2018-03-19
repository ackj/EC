package cn.itsite.order.contract;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.order.CategoryBean;
import cn.itsite.order.OrderBean;
import rx.Observable;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/19 0019 15:43
 */

public interface MineOrderContract {

    interface View extends BaseContract.View {
        void responseGetCategories(List<CategoryBean> data);
    }

    interface Presenter extends BaseContract.Presenter {
        void getCategories(String uid,String type);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseResponse<List<CategoryBean>>> getCategories(String uid,String type);
    }

}
