package cn.itsite.goodshome.contract;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.goodshome.HomePojo;
import cn.itsite.goodshome.StoreItemGridBean;
import rx.Observable;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/20 0020 10:06
 */

public interface HomeContract {
    interface View extends BaseContract.View{
        void responseGetHome(List<StoreItemGridBean> list);
    }

    interface Presenter extends BaseContract.Presenter {
        void getHome(String type);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseResponse<HomePojo>> getHome(String type);
    }
}
