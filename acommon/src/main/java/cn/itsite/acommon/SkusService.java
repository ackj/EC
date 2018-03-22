package cn.itsite.acommon;

import cn.itsite.abase.network.http.BaseResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/22 0022 14:42
 */

public interface SkusService {

    @GET("v1/skus/{uid}")
    Observable<BaseResponse<SkusBean>> getSkus(@Path("uid")String uid);


}
