package cn.itsite.delivery.model;

import java.util.List;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.abase.network.http.HttpHelper;
import cn.itsite.delivery.AddressBean;
import cn.itsite.delivery.AddressServer;
import cn.itsite.delivery.contract.AddressContract;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/14 0014 15:45
 */

public class AddressModel extends BaseModel implements AddressContract.Model{

    @Override
    public Observable<BaseResponse<List<AddressBean>>> getAddress() {
        return HttpHelper.getService(AddressServer.class)
                .getAddress()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResponse> deleteAddress() {
        return HttpHelper.getService(AddressServer.class)
                .deleteAddress("123")
                .subscribeOn(Schedulers.io());
    }
}
