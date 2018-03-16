package cn.itsite.classify.presenter;

import android.support.annotation.NonNull;

import java.util.List;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.classify.MenuBean;
import cn.itsite.classify.contract.MenuContract;
import cn.itsite.classify.model.MenuModel;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/16 0016 10:25
 */

public class MenuPresenter extends BasePresenter<MenuContract.View,MenuContract.Model> implements MenuContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public MenuPresenter(MenuContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected MenuContract.Model createModel() {
        return new MenuModel();
    }

    @Override
    public void getGategories(String uid) {
        mRxManager.add(mModel.getGategories(uid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<List<MenuBean>>>(){
                    @Override
                    public void onSuccess(BaseResponse<List<MenuBean>> listBaseResponse) {
                        getView().responseGetGategories(listBaseResponse.getData());
                    }
                }));
    }
}
