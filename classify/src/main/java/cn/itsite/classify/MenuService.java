package cn.itsite.classify;

import java.util.List;

import cn.itsite.abase.network.http.BaseResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/16 0016 10:29
 */

public interface MenuService {

    @GET("v1/categories")
    Observable<BaseResponse<List<MenuBean>>> getGategories(@Query("params") String params);

    @GET("v1/products")
    Observable<BaseResponse<List<ProductBean>>> getProducts(@Query("params") String params);

}
