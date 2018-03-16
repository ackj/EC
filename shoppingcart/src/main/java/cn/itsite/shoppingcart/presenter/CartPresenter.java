package cn.itsite.shoppingcart.presenter;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import java.util.List;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.shoppingcart.RecommendGoodsBean;
import cn.itsite.shoppingcart.StorePojo;
import cn.itsite.shoppingcart.UidBean;
import cn.itsite.shoppingcart.contract.CartContract;
import cn.itsite.shoppingcart.model.CartModel;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/12 0012 17:41
 */

public class CartPresenter extends BasePresenter<CartContract.View,CartContract.Model> implements CartContract.Presenter {

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
    public void deleteProduct(String shopUID,String productUID) {
        mRxManager.add(mModel.deleteProduct(shopUID,productUID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<List<UidBean>>>(){
                    @Override
                    public void onSuccess(BaseResponse<List<UidBean>> listBaseResponse) {
                        Logger.e("delete success"+listBaseResponse.getData().get(0).getUid());
                        getView().responseDeleteSuccess(listBaseResponse.getData());
                    }
                }));
    }

    @Override
    public void postProduct(String shopUID, String productUID) {
        mRxManager.add(mModel.postProduct(shopUID,productUID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<List<UidBean>>>(){
                    @Override
                    public void onSuccess(BaseResponse<List<UidBean>> listBaseResponse) {
                        Logger.e("post success"+listBaseResponse.getData().get(0).getUid());
                        getView().responsePostSuccess(listBaseResponse.getData());
                    }
                }));
    }

    @Override
    public void putProduct(String cartsUID, String productUID) {
        mRxManager.add(mModel.putProduct(cartsUID,productUID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<List<UidBean>>>(){
                    @Override
                    public void onSuccess(BaseResponse<List<UidBean>> listBaseResponse) {
                        Logger.e("post success"+listBaseResponse.getData().get(0).getUid());
                        getView().responsePutSuccess(listBaseResponse.getData());
                    }
                }));
    }

    @Override
    public void getCarts(String cartsUID) {
        mRxManager.add(mModel.getCarts(cartsUID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<List<StorePojo>>>(){
                    @Override
                    public void onSuccess(BaseResponse<List<StorePojo>> listBaseResponse) {
                        getView().responseGetCartsSuccess(listBaseResponse.getData());
                    }
                }));
    }

    @Override
    public void getRecommendGoods() {
        mRxManager.add(mModel.getRecommendGoods()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<List<RecommendGoodsBean>>>(){
                    @Override
                    public void onSuccess(BaseResponse<List<RecommendGoodsBean>> listBaseResponse) {
                        getView().responseRecommendGoodsSuccess(listBaseResponse.getData());
                    }
                }));
    }
}
