package cn.itsite.classify.model;

import java.util.List;

import cn.itsite.abase.mvp.model.base.BaseModel;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.abase.network.http.HttpHelper;
import cn.itsite.acommon.Params;
import cn.itsite.classify.MenuBean;
import cn.itsite.classify.MenuService;
import cn.itsite.classify.ProductBean;
import cn.itsite.classify.contract.MenuContract;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/16 0016 10:23
 */

public class MenuModel extends BaseModel implements MenuContract.Model {

    @Override
    public Observable<BaseResponse<List<MenuBean>>> getGategories(Params params) {
        return HttpHelper.getService(MenuService.class)
                .getGategories(params.toString())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResponse<List<ProductBean>>> getProducts(Params params) {
        return HttpHelper.getService(MenuService.class)
                .getProducts(params.toString())
                .subscribeOn(Schedulers.io());
    }


}
