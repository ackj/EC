package cn.itsite.goodssearch.model;

import java.util.List;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.abase.network.http.HttpHelper;
import cn.itsite.acommon.Params;
import cn.itsite.goodssearch.GoodsBean;
import cn.itsite.goodssearch.KeywordBean;
import cn.itsite.goodssearch.KeywordService;
import cn.itsite.goodssearch.contract.KeywordsContract;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/14 0014 16:52
 */

public class KeywordsModel extends BaseModel implements KeywordsContract.Model{

    @Override
    public Observable<BaseResponse<List<KeywordBean>>> getKeywords(Params params) {
        return HttpHelper.getService(KeywordService.class)
                .getKeywords(params.toString())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResponse<List<GoodsBean>>> getProducts(Params params) {
        return HttpHelper.getService(KeywordService.class)
                .getProducts(params.toString())
                .subscribeOn(Schedulers.io());
    }
}
