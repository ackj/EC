package cn.itsite.order.presenter;

import android.support.annotation.NonNull;

import java.util.List;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.order.OrderBean;
import cn.itsite.order.contract.OrderContract;
import cn.itsite.order.model.OrderModel;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/15 0015 11:53
 */

public class OrderPresenter extends BasePresenter<OrderContract.View,OrderContract.Model> implements OrderContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public OrderPresenter(OrderContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected OrderContract.Model createModel() {
        return new OrderModel();
    }

    @Override
    public void getOrder(int state) {
        mRxManager.add(mModel.getOrder(state)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<List<OrderBean>>>(){
                    @Override
                    public void onSuccess(BaseResponse<List<OrderBean>> listBaseResponse) {
                        getView().responseGetOrder(listBaseResponse.getData());
                    }
                }));
    }
}
