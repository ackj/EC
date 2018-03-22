package cn.itsite.acommon.presenter;

import android.support.annotation.NonNull;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.acommon.SkusBean;
import cn.itsite.acommon.contract.SkusContract;
import cn.itsite.acommon.model.SkusModel;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/22 0022 14:39
 */

public class SkusPresenter extends BasePresenter<SkusContract.View,SkusContract.Model> implements SkusContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public SkusPresenter(SkusContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected SkusContract.Model createModel() {
        return new SkusModel();
    }

    @Override
    public void getSkus(String uid) {
        mRxManager.add(mModel.getSkus(uid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<SkusBean>>(){
                    @Override
                    public void onSuccess(BaseResponse<SkusBean> response) {
                        getView().responseGetSkus(response.getData());
                    }
                }));
    }
}
