package cn.itsite.shoppingcart.contract;

import java.util.List;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.acommon.Params;
import cn.itsite.shoppingcart.RecommendGoodsBean;
import cn.itsite.shoppingcart.StoreBean;
import cn.itsite.shoppingcart.StorePojo;
import cn.itsite.shoppingcart.UidBean;
import rx.Observable;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/12 0012 15:07
 */

public interface CartContract {

    interface View extends BaseContract.View{
        void responseDeleteSuccess(List<UidBean> data);
        void responsePostSuccess(List<UidBean> data);
        void responsePutSuccess(List<UidBean> data);
        void responseGetCartsSuccess(List<StoreBean> data);
        void responseRecommendGoodsSuccess(List<StoreBean> data);
    }

    interface Presenter extends BaseContract.Presenter {
        void deleteProduct(String cartsUID,String productUID);
        void postProduct(String cartsUID,String productUID);
        void putProduct(String cartsUID,String productUID);
        void getCarts(String cartsUID,Params params);
        void getRecommendGoods();
    }

    interface Model extends BaseContract.Model {
        Observable<BaseResponse<List<UidBean>>> deleteProduct(String shopUID, String productUID);
        Observable<BaseResponse<List<UidBean>>> postProduct(String shopUID, String productUID);
        Observable<BaseResponse<List<UidBean>>> putProduct(String shopUID, String productUID);
        Observable<BaseResponse<List<StorePojo>>> getCarts(String shopUID,Params params);
        Observable<BaseResponse<List<RecommendGoodsBean>>> getRecommendGoods();
    }
}
