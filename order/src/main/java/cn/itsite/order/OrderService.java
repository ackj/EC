package cn.itsite.order;

import java.util.List;

import cn.itsite.abase.network.http.BaseResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/15 0015 11:56
 */

public interface OrderService {

    //获取订单分类字符串
    @GET("v1/categories")
    Observable<BaseResponse<List<CategoryBean>>> getCategories(@Query("params")String params);

    //获取订单列表
    @GET("v1/orders")
    Observable<BaseResponse<List<OrderBean>>> getOrder(@Query("params")String params);

    //获取订单详情
    @GET("v1/orders/{uid}")
    Observable<BaseResponse<OrderDetailBean>> getOrderDetail(@Query("params")String params);
}
