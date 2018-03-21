package cn.itsite.delivery.presenter;

import android.support.annotation.NonNull;

import java.util.List;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.delivery.DeliveryBean;
import cn.itsite.delivery.contract.DeliveryContract;
import cn.itsite.delivery.model.DeliveryModel;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/14 0014 15:45
 */

public class DeliveryPresenter extends BasePresenter<DeliveryContract.View,DeliveryContract.Model> implements DeliveryContract.Presenter{

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public DeliveryPresenter(DeliveryContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected DeliveryContract.Model createModel() {
        return new DeliveryModel();
    }

    @Override
    public void getAddress() {
        mRxManager.add(mModel.getAddress()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<List<DeliveryBean>>>(){
                    @Override
                    public void onSuccess(BaseResponse<List<DeliveryBean>> listBaseResponse) {
                        getView().responseGetAddress(listBaseResponse.getData());
                    }
                }));
    }

    @Override
    public void deleteAddress() {
        mRxManager.add(mModel.deleteAddress()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(){
                    @Override
                    public void onSuccess(BaseResponse listBaseResponse) {
                        getView().responseDeleteAddressSuccess();
                    }
                }));
    }
}
