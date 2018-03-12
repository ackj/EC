package cn.itsite.goodssearch.contract;


import java.util.List;

import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.goodssearch.KeywordBean;
import retrofit2.http.GET;
import rx.Observable;

/**
 * @author leguang
 * @version v0.0.0
 * @E-mail langmanleguang@qq.com
 * @time 2018/3/12 0012 15:29
 */
public interface KeywordService {

    @GET("v1/keywords")
    Observable<BaseResponse<List<KeywordBean>>> getKeywords();
}
