package cn.itsite.delivery.presenter;

import android.support.annotation.NonNull;

import java.util.List;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.delivery.AddressBean;
import cn.itsite.delivery.contract.AddressContract;
import cn.itsite.delivery.model.AddressModel;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/14 0014 15:45
 */

public class AddressPresenter extends BasePresenter<AddressContract.View,AddressContract.Model> implements AddressContract.Presenter{

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public AddressPresenter(AddressContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected AddressContract.Model createModel() {
        return new AddressModel();
    }

    @Override
    public void getAddress() {
        mRxManager.add(mModel.getAddress()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<List<AddressBean>>>(){
                    @Override
                    public void onSuccess(BaseResponse<List<AddressBean>> listBaseResponse) {
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
