package cn.itsite.goodssearch.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.List;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import cn.itsite.abase.network.http.BaseResponse;
import cn.itsite.goodssearch.GoodsBean;
import cn.itsite.goodssearch.KeywordBean;
import cn.itsite.goodssearch.contract.KeywordsContract;
import cn.itsite.goodssearch.model.KeywordsModel;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/14 0014 16:52
 */

public class KeywordsPresenter extends BasePresenter<KeywordsContract.View,KeywordsContract.Model> implements KeywordsContract.Presenter{

    /**
     * 创建Presenter的时候就绑定View和创建model。
     *
     * @param mView 所要绑定的view层对象，一般在View层创建Presenter的时候通过this把自己传过来。
     */
    public KeywordsPresenter(KeywordsContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected KeywordsContract.Model createModel() {
        return new KeywordsModel();
    }

    @Override
    public void getKeywords(String keywords) {
        mRxManager.add(mModel.getKeywords(keywords)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<List<KeywordBean>>>(){
                    @Override
                    public void onSuccess(BaseResponse<List<KeywordBean>> listBaseResponse) {
                        if(TextUtils.isEmpty(keywords)){
                            getView().responseGetHotKeywords(listBaseResponse.getData());
                        }else{
                            getView().responseGetKeywords(listBaseResponse.getData());
                        }
                    }
                }));
    }

    @Override
    public void getProducts(String keywords) {
        mRxManager.add(mModel.getProducts(keywords)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<List<GoodsBean>>>(){
                    @Override
                    public void onSuccess(BaseResponse<List<GoodsBean>> listBaseResponse) {
                        getView().responseGetProducts(listBaseResponse.getData());
                    }
                }));
    }
}
