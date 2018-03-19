package cn.itsite.order.presenter;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import java.util.List;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.order.OrderBean;
import cn.itsite.order.contract.OrderListContract;
import cn.itsite.order.model.OrderListModel;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/19 0019 16:42
 */

public class OrderListPresenter extends BasePresenter<OrderListContract.View,OrderListContract.Model> implements OrderListContract.Presenter {

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public OrderListPresenter(OrderListContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected OrderListContract.Model createModel() {
        return new OrderListModel();
    }

    @Override
    public void getOrders(String category) {
        mRxManager.add(mModel.getOrders(category)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<List<OrderBean>>>() {
                    @Override
                    public void onSuccess(BaseResponse<List<OrderBean>> listBaseResponse) {
                        Logger.e("post success" + listBaseResponse.getData().get(0).getUid());
                        getView().responseOrders(listBaseResponse.getData());
                    }
                }));
    }
}
