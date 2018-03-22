package cn.itsite.acommon.model;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.abase.network.http.HttpHelper;
import cn.itsite.acommon.SkusBean;
import cn.itsite.acommon.SkusService;
import cn.itsite.acommon.contract.SkusContract;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/22 0022 14:39
 */

public class SkusModel extends BaseModel implements SkusContract.Model{

    @Override
    public Observable<BaseResponse<SkusBean>> getSkus(String uid) {
        return HttpHelper.getService(SkusService.class)
                .getSkus(uid)
                .subscribeOn(Schedulers.io());
    }
}
