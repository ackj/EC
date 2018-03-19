package cn.itsite.order.presenter;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import java.util.List;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.order.CategoryBean;
import cn.itsite.order.OrderBean;
import cn.itsite.order.contract.MineOrderContract;
import cn.itsite.order.model.MineOrderModel;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/19 0019 16:24
 */

public class MineOrderPresenter extends BasePresenter<MineOrderContract.View,MineOrderContract.Model> implements MineOrderContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public MineOrderPresenter(MineOrderContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected MineOrderContract.Model createModel() {
        return new MineOrderModel();
    }

    @Override
    public void getCategories(String uid, String type) {
        mRxManager.add(mModel.getCategories(uid,type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<List<CategoryBean>>>() {
                    @Override
                    public void onSuccess(BaseResponse<List<CategoryBean>> listBaseResponse) {
                        Logger.e("post success" + listBaseResponse.getData().get(0).getUid());
                        getView().responseGetCategories(listBaseResponse.getData());
                    }
                }));
    }

}
