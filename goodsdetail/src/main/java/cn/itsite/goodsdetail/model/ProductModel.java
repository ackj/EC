package cn.itsite.goodsdetail.model;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.abase.network.http.HttpHelper;
import cn.itsite.goodsdetail.ProductDetailBean;
import cn.itsite.goodsdetail.ProductService;
import cn.itsite.goodsdetail.contract.ProductContract;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/21 0021 17:38
 */

public class ProductModel extends BaseModel implements ProductContract.Model{

    @Override
    public Observable<BaseResponse<ProductDetailBean>> getProduct(String uid) {
        return HttpHelper.getService(ProductService.class)
                .getProduct(uid)
                .subscribeOn(Schedulers.io());
    }

}
