package cn.itsite.acommon.contract;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.acommon.SkusBean;
import rx.Observable;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/22 0022 14:37
 */

public interface SkusContract {

    interface View extends BaseContract.View{
        void responseGetSkus(SkusBean bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void getSkus(String uid);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseResponse<SkusBean>> getSkus(String uid);
    }

}
