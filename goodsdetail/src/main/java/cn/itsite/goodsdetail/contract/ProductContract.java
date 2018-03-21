package cn.itsite.goodsdetail.contract;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.goodsdetail.ProductDetailBean;
import rx.Observable;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/21 0021 17:38
 */

public interface ProductContract {
    interface View extends BaseContract.View{
        void responseGetProduct(ProductDetailBean bean);
    }

    interface Presenter extends BaseContract.Presenter {
        void getProduct(String uid);
    }

    interface Model extends BaseContract.Model {
        Observable<BaseResponse<ProductDetailBean>> getProduct(String uid);
    }
}
