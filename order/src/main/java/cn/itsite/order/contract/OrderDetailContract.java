package cn.itsite.order.contract;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.acommon.Params;
import cn.itsite.order.OrderDetailBean;
import rx.Observable;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/20 0020 16:43
 */

public interface OrderDetailContract {

    interface View extends BaseContract.View {
        void responseOrderDetail(OrderDetailBean orderDetailBean);
    }

    interface Presenter extends BaseContract.Presenter {
        void getOrderDetail(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseResponse<OrderDetailBean>> getOrderDetail(Params params);
    }

}
