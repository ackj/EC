package cn.itsite.goodshome.model;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.abase.network.http.HttpHelper;
import cn.itsite.acommon.Params;
import cn.itsite.goodshome.HomePojo;
import cn.itsite.goodshome.HomeService;
import cn.itsite.goodshome.contract.HomeContract;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/20 0020 10:18
 */

public class HomeModel extends BaseModel implements HomeContract.Model {

    @Override
    public Observable<BaseResponse<HomePojo>> getHome(Params params) {
        return HttpHelper.getService(HomeService.class)
                .getHome(params.toString())
                .subscribeOn(Schedulers.io());
    }
}
