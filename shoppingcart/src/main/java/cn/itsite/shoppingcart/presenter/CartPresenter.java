package cn.itsite.shoppingcart.presenter;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.shoppingcart.RecommendGoodsBean;
import cn.itsite.shoppingcart.StoreBean;
import cn.itsite.shoppingcart.StorePojo;
import cn.itsite.shoppingcart.UidBean;
import cn.itsite.shoppingcart.contract.CartContract;
import cn.itsite.shoppingcart.model.CartModel;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/12 0012 17:41
 */

public class CartPresenter extends BasePresenter<CartContract.View, CartContract.Model> implements CartContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public CartPresenter(CartContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected CartContract.Model createModel() {
        return new CartModel();
    }

    @Override
    public void deleteProduct(String shopUID, String productUID) {
        mRxManager.add(mModel.deleteProduct(shopUID, productUID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<List<UidBean>>>() {
                    @Override
                    public void onSuccess(BaseResponse<List<UidBean>> listBaseResponse) {
                        Logger.e("delete success" + listBaseResponse.getData().get(0).getUid());
                        getView().responseDeleteSuccess(listBaseResponse.getData());
                    }
                }));
    }

    @Override
    public void postProduct(String shopUID, String productUID) {
        mRxManager.add(mModel.postProduct(shopUID, productUID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<List<UidBean>>>() {
                    @Override
                    public void onSuccess(BaseResponse<List<UidBean>> listBaseResponse) {
                        getView().responsePostSuccess(listBaseResponse.getData());
                    }
                }));
    }

    @Override
    public void putProduct(String cartsUID, String productUID) {
        mRxManager.add(mModel.putProduct(cartsUID, productUID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<List<UidBean>>>() {
                    @Override
                    public void onSuccess(BaseResponse<List<UidBean>> listBaseResponse) {
                        Logger.e("post success" + listBaseResponse.getData().get(0).getUid());
                        getView().responsePutSuccess(listBaseResponse.getData());
                    }
                }));
    }

    @Override
    public void getCarts(String cartsUID) {
        mRxManager.add(mModel.getCarts(cartsUID)
                .map((Func1<BaseResponse<List<StorePojo>>, List<StoreBean>>) response -> {
                    List<StorePojo> data = response.getData();
                    List<StoreBean> resultData = new ArrayList<>();
                    for (int i = 0; i < data.size(); i++) {
                        StoreBean shopBean = new StoreBean();
                        shopBean.setItemType(StoreBean.TYPE_STORE_TITLE);
                        shopBean.setShopBean(data.get(i).getShop());
                        //设置商品个数，为刷新用
                        shopBean.setGoodsCount(data.get(i).getProducts().size());
                        shopBean.setSpanSize(2);
                        resultData.add(shopBean);
                        for (int j = 0; j < data.get(i).getProducts().size(); j++) {
                            StoreBean productBean = new StoreBean();
                            productBean.setItemType(StoreBean.TYPE_STORE_GOODS);
                            productBean.setProductsBean(data.get(i).getProducts().get(j));
                            productBean.setSpanSize(2);
                            resultData.add(productBean);
                        }
                    }
                    return resultData;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<StoreBean>>() {
                    @Override
                    public void onCompleted() {
                        complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        error(e);
                    }

                    @Override
                    public void onNext(List<StoreBean> list) {
                        getView().responseGetCartsSuccess(list);
                    }
                }));
    }

    @Override
    public void getRecommendGoods() {
        mRxManager.add(mModel.getRecommendGoods()
                .map(new Func1<BaseResponse<List<RecommendGoodsBean>>, List<StoreBean>>() {
                    @Override
                    public List<StoreBean> call(BaseResponse<List<RecommendGoodsBean>> listBaseResponse) {
                        List<StoreBean> resultData = new ArrayList<>();
                        List<RecommendGoodsBean> data = listBaseResponse.getData();
                        if (data.size() > 0) {
                            StoreBean recommendTitle = new StoreBean();
                            recommendTitle.setItemType(StoreBean.TYPE_RECOMMEND_TITLE);
                            recommendTitle.setSpanSize(2);
                            resultData.add(recommendTitle);
                        }
                        for (int i = 0; i < data.size(); i++) {
                            StoreBean recommendBean = new StoreBean();
                            recommendBean.setItemType(StoreBean.TYPE_RECOMMEND_GOODS);
                            recommendBean.setRecommendGoodsBean(data.get(i));
                            recommendBean.setSpanSize(1);
                            resultData.add(recommendBean);
                        }
                        return resultData;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<StoreBean>>() {
                    @Override
                    public void onCompleted() {
                        complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        error(e);
                    }

                    @Override
                    public void onNext(List<StoreBean> list) {
                        getView().responseRecommendGoodsSuccess(list);
                    }
                }));
    }
}
