package cn.itsite.goodsdetail;

import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.acommon.SkusBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/21 0021 17:47
 */

public interface ProductService {

    @GET("v1/products/{uid}")
    Observable<BaseResponse<ProductDetailBean>> getProduct(@Path("uid")String uid);

    @GET("v1/skus")
    Observable<SkusBean> getSkus(@Query("uid")String uid);

}
