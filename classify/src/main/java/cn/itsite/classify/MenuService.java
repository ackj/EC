package cn.itsite.classify;

import java.util.List;

import cn.itsite.abase.network.http.BaseResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/16 0016 10:29
 */

public interface MenuService {

    @GET("/v1/categories/{uid}")
    Observable<BaseResponse<List<MenuBean>>> getGategories(@Path("uid")String uid);

    @GET("/v1/products")
    Observable<BaseResponse<List<ProductBean>>> getProducts(@Path("uid")String uid);

}
