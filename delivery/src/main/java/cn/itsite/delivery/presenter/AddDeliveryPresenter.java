package cn.itsite.delivery.presenter;

import android.support.annotation.NonNull;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.delivery.contract.AddDeliveryContract;
import cn.itsite.delivery.model.AddDeliveryModel;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/15 0015 10:30
 */

public class AddDeliveryPresenter extends BasePresenter<AddDeliveryContract.View,AddDeliveryContract.Model> implements AddDeliveryContract.Presenter {


    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public AddDeliveryPresenter(AddDeliveryContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected AddDeliveryContract.Model createModel() {
        return new AddDeliveryModel();
    }

    @Override
    public void postAddress() {
        mRxManager.add(mModel.postAddress()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(){
                    @Override
                    public void onSuccess(BaseResponse listBaseResponse) {
                        getView().responsePostAddressSuccess();
                    }
                }));
    }

    @Override
    public void putAddress() {
        mRxManager.add(mModel.putAddress()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(){
                    @Override
                    public void onSuccess(BaseResponse listBaseResponse) {
                        getView().responsePutAddressSuccess();
                    }
                }));
    }
}
