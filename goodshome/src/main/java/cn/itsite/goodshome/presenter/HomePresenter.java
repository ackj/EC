package cn.itsite.goodshome.presenter;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.goodshome.HomePojo;
import cn.itsite.goodshome.StoreItemGridBean;
import cn.itsite.goodshome.contract.HomeContract;
import cn.itsite.goodshome.model.HomeModel;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/20 0020 10:18
 */

public class HomePresenter extends BasePresenter<HomeContract.View, HomeContract.Model> implements HomeContract.Presenter {
    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public HomePresenter(HomeContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected HomeContract.Model createModel() {
        return new HomeModel();
    }

    @Override
    public void getHome(String type) {
        mRxManager.add(mModel.getHome(type)
                .map(new Func1<BaseResponse<HomePojo>, List<StoreItemGridBean>>() {
                    @Override
                    public List<StoreItemGridBean> call(BaseResponse<HomePojo> homePojoBaseResponse) {
                        HomePojo homeBean = homePojoBaseResponse.getData();
                        List<StoreItemGridBean> data = new ArrayList<>();
                        //banners
                        StoreItemGridBean banners = new StoreItemGridBean();
                        banners.setBanners(homeBean.getAds());
                        banners.setSpanSize(2);
                        banners.setItemType(StoreItemGridBean.TYPE_BANNER);
                        data.add(banners);
                        for (int i = 0; i < homeBean.getRecommendations().size(); i++) {
                            StoreItemGridBean categoryBean = new StoreItemGridBean();
                            categoryBean.setCategoryBean(homeBean.getRecommendations().get(i).getCategory());
                            categoryBean.setSpanSize(2);
                            categoryBean.setItemType(StoreItemGridBean.TYPE_MORE);
                            data.add(categoryBean);
                            for (int j =0;j<homeBean.getRecommendations().get(i).getProducts().size();j++){
                                HomePojo.RecommendationsBean recommendationsBean = homeBean.getRecommendations().get(i);
                                if(j==0){
                                    StoreItemGridBean recommendBean = new StoreItemGridBean();
                                    recommendBean.setProductsBean(recommendationsBean.getProducts().get(0));
                                    recommendBean.setSpanSize(2);
                                    recommendBean.setItemType(StoreItemGridBean.TYPE_RECOMMEND);
                                    data.add(recommendBean);
                                }else{
                                    StoreItemGridBean productBean = new StoreItemGridBean();
                                    productBean.setProductsBean(recommendationsBean.getProducts().get(j));
                                    productBean.setSpanSize(1);
                                    productBean.setItemType(StoreItemGridBean.TYPE_GOODS);
                                    data.add(productBean);
                                }
                            }
                        }
                        return data;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<StoreItemGridBean>>() {
                    @Override
                    public void onCompleted() {
                        complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        error(e);
                    }

                    @Override
                    public void onNext(List<StoreItemGridBean> storeItemGridBeans) {
                        getView().responseGetHome(storeItemGridBeans);
                    }
                }));
    }
}
