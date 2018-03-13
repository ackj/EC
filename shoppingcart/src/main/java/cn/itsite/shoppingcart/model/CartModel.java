package cn.itsite.shoppingcart.model;


import java.util.List;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.abase.network.http.HttpHelper;
import cn.itsite.shoppingcart.CartBean;
import cn.itsite.shoppingcart.CartServer;
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
    public Observable<BaseResponse<List<CartBean>>> deleteCart(String shopUID) {
        return HttpHelper.getService(CartServer.class)
                .deleteCart(shopUID)
                .subscribeOn(Schedulers.io());
    }


}
