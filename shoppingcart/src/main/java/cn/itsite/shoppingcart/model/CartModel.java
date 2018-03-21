package cn.itsite.shoppingcart.model;


import java.util.List;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.abase.network.http.HttpHelper;
import cn.itsite.acommon.Params;
import cn.itsite.shoppingcart.CartService;
import cn.itsite.shoppingcart.RecommendGoodsBean;
import cn.itsite.shoppingcart.RequestBean;
import cn.itsite.shoppingcart.StorePojo;
import cn.itsite.shoppingcart.UidBean;
import cn.itsite.shoppingcart.contract.CartContract;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/12 0012 17:40
 */

public class CartModel extends BaseModel implements CartContract.Model {

    @Override
    public Observable<BaseResponse<List<UidBean>>> deleteProduct(String shopUID, String productUID) {
        RequestBean bean = new RequestBean();
        RequestBean.DataBean data = new RequestBean.DataBean();
        data.setUid("12312312");
        bean.setData(data);
        bean.setMessage("hahahah");
        return HttpHelper.getService(CartService.class)
                .deleteProduct(shopUID, productUID, bean)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResponse<List<UidBean>>> postProduct(String shopUID, String productUID) {
        RequestBean bean = new RequestBean();
        RequestBean.DataBean data = new RequestBean.DataBean();
        data.setUid("12312312");
        data.setAmount("12312312");
        bean.setData(data);
        bean.setMessage("hahahah");
        return HttpHelper.getService(CartService.class)
                .postProduct(shopUID, productUID, bean)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResponse<List<UidBean>>> putProduct(String shopUID, String productUID) {
        RequestBean bean = new RequestBean();
        RequestBean.DataBean data = new RequestBean.DataBean();
        data.setUid("12312312");
        data.setAmount("12312312");
        bean.setData(data);
        bean.setMessage("hahahah");
        return HttpHelper.getService(CartService.class)
                .putProduct(shopUID, productUID, bean)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResponse<List<StorePojo>>> getCarts(String shopUID,Params params) {
         return HttpHelper.getService(CartService.class)
                .getCarts(shopUID, params.toString())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResponse<List<RecommendGoodsBean>>> getRecommendGoods() {
        return HttpHelper.getService(CartService.class)
                .getRecommendGoods()
                .subscribeOn(Schedulers.io());
    }
}
