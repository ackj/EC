package cn.itsite.shoppingcart.presenter;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import java.util.List;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.shoppingcart.CartBean;
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
    public void deleteCart(String shopUID) {
        mRxManager.add(mModel.deleteCart(shopUID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<List<CartBean>>>(){

                    @Override
                    public void onSuccess(BaseResponse<List<CartBean>> listBaseResponse) {
                        Logger.e("delete success"+listBaseResponse.getData().get(0).getUid());
                    }
                }));
    }
}
