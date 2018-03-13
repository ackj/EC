package cn.itsite.shoppingcart.contract;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.shoppingcart.CartBean;
import rx.Observable;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/12 0012 15:07
 */

public interface CartContract {

    interface View extends BaseContract.View{
    }

    interface Presenter extends BaseContract.Presenter {
        void deleteCart(String shopUID);

    }

    interface Model extends BaseContract.Model {
        Observable<BaseResponse<List<CartBean>>> deleteCart(String shopUID);
    }
}
