package cn.itsite.order.presenter;

import android.support.annotation.NonNull;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.acommon.Params;
import cn.itsite.order.OrderDetailBean;
import cn.itsite.order.contract.OrderDetailContract;
import cn.itsite.order.model.OrderDetailModel;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/20 0020 16:46
 */

public class OrderDetailPresenter extends BasePresenter<OrderDetailContract.View,OrderDetailContract.Model> implements OrderDetailContract.Presenter{

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public OrderDetailPresenter(OrderDetailContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected OrderDetailContract.Model createModel() {
        return new OrderDetailModel();
    }

    @Override
    public void getOrderDetail(Params params) {
        mRxManager.add(mModel.getOrderDetail(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<OrderDetailBean>>() {
                    @Override
                    public void onSuccess(BaseResponse<OrderDetailBean> listBaseResponse) {
                        getView().responseOrderDetail(listBaseResponse.getData());
                    }
                }));
    }
}
