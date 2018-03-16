package cn.itsite.order.model;

import java.util.List;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.abase.network.http.HttpHelper;
import cn.itsite.order.OrderBean;
import cn.itsite.order.OrderService;
import cn.itsite.order.contract.OrderContract;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/15 0015 11:51
 */
public class OrderModel extends BaseModel implements OrderContract.Model{

    @Override
    public Observable<BaseResponse<List<OrderBean>>> getOrder(int state) {
        return HttpHelper.getService(OrderService.class)
                .getOrder(state)
                .subscribeOn(Schedulers.io());
    }
}
