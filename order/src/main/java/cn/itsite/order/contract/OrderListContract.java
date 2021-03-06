package cn.itsite.order.contract;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.acommon.Params;
import cn.itsite.order.OrderBean;
import rx.Observable;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/19 0019 16:39
 */

public interface OrderListContract {

    interface View extends BaseContract.View {
        void responseOrders(List<OrderBean> data);
    }

    interface Presenter extends BaseContract.Presenter {
        void getOrders(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseResponse<List<OrderBean>>> getOrders(Params params);
    }

}
