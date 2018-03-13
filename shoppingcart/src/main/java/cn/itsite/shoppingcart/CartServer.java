package cn.itsite.shoppingcart;

import java.util.List;

import cn.itsite.abase.network.http.BaseResponse;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Author： Administrator on 2018/3/12 0012.
 * Email： liujia95me@126.com
 */

public interface CartServer {

    @DELETE("/v1/carts/{shopUID}")
    Observable<BaseResponse<List<CartBean>>> deleteCart(@Path("shopUID") String shopUID);

    @GET("/v1/carts/{shopUID}")
    Observable<BaseResponse> getCart(@Path("shopUID") String shopUID);

    @POST("/v1/carts/{shopUID}")
    Observable<BaseResponse> postCart(@Path("shopUID") String shopUID);

    @PUT("/v1/carts/{shopUID}")
    Observable<BaseResponse> putCart(@Path("shopUID") String shopUID);

}
