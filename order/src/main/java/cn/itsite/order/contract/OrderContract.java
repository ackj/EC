package cn.itsite.order.contract;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.order.OrderBean;
import rx.Observable;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/15 0015 11:49
 */

public interface OrderContract {
    interface View extends BaseContract.View {
        void responseGetOrder(List<OrderBean> data);
    }

    interface Presenter extends BaseContract.Presenter {
        void getOrder(int state);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseResponse<List<OrderBean>>> getOrder(int state);
    }
}
