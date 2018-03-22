package cn.itsite.shoppingcart;

import java.util.List;

import cn.itsite.abase.network.http.BaseResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author： Administrator on 2018/3/12 0012.
 * Email： liujia95me@126.com
 */

public interface CartService {

    //删除一个商品
    @HTTP(method = "DELETE",path = "v1/carts/{cartsUID}/products/{productUID}",hasBody = true)
    Observable<BaseResponse<List<UidBean>>> deleteProduct(@Path("cartsUID") String cartsUID, @Path("productUID") String productUID, @Body RequestBean body);

    //增加一个商品
    @POST("v1/carts/{cartsUID}/products/{productUID}")
    Observable<BaseResponse<List<UidBean>>> postProduct(@Path("cartsUID") String cartsUID, @Path("productUID") String productUID, @Body RequestBean body);

    //
    @POST("v1/carts/{cartsUID}/products/{productUID}")
    Observable<BaseResponse<List<UidBean>>> putProduct(@Path("cartsUID") String cartsUID, @Path("productUID") String productUID, @Body RequestBean body);

    //获取购物车列表
    @GET("v1/carts/{cartsUID}")
    Observable<BaseResponse<List<StorePojo>>> getCarts(@Path("cartsUID") String cartsUID);

    //获取推荐商品列表
    @GET("v1/products")
    Observable<BaseResponse<List<RecommendGoodsBean>>> getRecommendGoods(@Query("params")String params);

}
