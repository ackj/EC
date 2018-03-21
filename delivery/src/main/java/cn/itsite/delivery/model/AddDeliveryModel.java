package cn.itsite.delivery.model;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.abase.network.http.HttpHelper;
import cn.itsite.delivery.DeliveryService;
import cn.itsite.delivery.RequestBean;
import cn.itsite.delivery.contract.AddDeliveryContract;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/15 0015 10:24
 */

public class AddDeliveryModel extends BaseModel implements AddDeliveryContract.Model {

    @Override
    public Observable<BaseResponse> postAddress() {
        RequestBean requestBean = new RequestBean();
        List<RequestBean.DataBean> list = new ArrayList<>();
        RequestBean.DataBean bean = new RequestBean.DataBean();
        bean.setAddress("啦啦啦");
        bean.setDeafult(true);
        bean.setGender("啦啦啦");
        bean.setLatitude("啦啦啦");
        bean.setLocation("啦啦啦");
        bean.setName("啦啦啦");
        bean.setPhoneNumber("啦啦啦");
        list.add(bean);
        requestBean.setData(list);
        return HttpHelper.getService(DeliveryService.class)
                .postAddress(requestBean)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResponse> putAddress() {
        RequestBean requestBean = new RequestBean();
        List<RequestBean.DataBean> list = new ArrayList<>();
        RequestBean.DataBean bean = new RequestBean.DataBean();
        bean.setAddress("啦啦啦");
        bean.setDeafult(true);
        bean.setGender("啦啦啦");
        bean.setLatitude("啦啦啦");
        bean.setLocation("啦啦啦");
        bean.setName("啦啦啦");
        bean.setPhoneNumber("啦啦啦");
        list.add(bean);
        requestBean.setData(list);
        return HttpHelper.getService(DeliveryService.class)
                .putAddress("123",requestBean)
                .subscribeOn(Schedulers.io());
    }
}
