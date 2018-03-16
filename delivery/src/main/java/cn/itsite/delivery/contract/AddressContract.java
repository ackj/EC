package cn.itsite.delivery.contract;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.delivery.AddressBean;
import rx.Observable;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/14 0014 15:45
 */

public interface AddressContract {
    interface View extends BaseContract.View{
        void responseGetAddress(List<AddressBean> data);
        void responseDeleteAddressSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        void getAddress();
        void deleteAddress();
    }

    interface Model extends BaseContract.Model {
        Observable<BaseResponse<List<AddressBean>>> getAddress();
        Observable<BaseResponse> deleteAddress();
    }
}
