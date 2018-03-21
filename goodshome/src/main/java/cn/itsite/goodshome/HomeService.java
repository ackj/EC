package cn.itsite.goodshome;

import cn.itsite.abase.network.http.BaseResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/20 0020 10:10
 */

public interface HomeService {


    @GET("/v1/home")
    Observable<BaseResponse<HomePojo>> getHome(@Query("params")String params);

}
