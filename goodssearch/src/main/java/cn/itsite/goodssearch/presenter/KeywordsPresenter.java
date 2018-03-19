package cn.itsite.goodssearch.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.BaseApp;
import cn.itsite.abase.cache.SPCache;
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

    private static final String KEY_WORD = "keyword";

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
        //存到历史记录中
        saveKeyword2Local(keywords);
        mRxManager.add(mModel.getProducts(keywords)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<List<GoodsBean>>>(){
                    @Override
                    public void onSuccess(BaseResponse<List<GoodsBean>> listBaseResponse) {
                        getView().responseGetProducts(listBaseResponse.getData());
                    }
                }));
    }

    public void saveKeyword2Local(String name) {
        String keywordStr = (String) SPCache.get(BaseApp.mContext, KEY_WORD, "");
        if (TextUtils.isEmpty(keywordStr)) {
            keywordStr = name;
        } else {
            if (keywordStr.contains(name)) {
                if (keywordStr.startsWith(name)) {
                    return;
                } else {
                    String[] split = keywordStr.split("," + name);
                    keywordStr = split.length == 2 ? split[0] + split[1] : split[0];
                    Logger.d(TAG, "contains substring --> :" + keywordStr);
                }
            } else {
                String[] split = keywordStr.split(",");
                //最多存9个
                if (split.length >= 9) {
                    Logger.d(TAG, "indexof:" + keywordStr.lastIndexOf(","));
                    keywordStr = keywordStr.substring(0, keywordStr.lastIndexOf(","));
                }
            }
            keywordStr = name + "," + keywordStr;
        }
        Logger.d(TAG, "save citysStr:" + keywordStr);
        SPCache.put(BaseApp.mContext, KEY_WORD, keywordStr);
    }

    public List<String> getKeyword2Local(){
        List<String> keywords = new ArrayList<>();
        String keywrodsStr = (String) SPCache.get(BaseApp.mContext, KEY_WORD, "");
        Logger.d(TAG, "citysStr:" + keywrodsStr);
        if (!TextUtils.isEmpty(keywrodsStr)) {
            String[] keywordsArr = keywrodsStr.split(",");
            for (String city : keywordsArr) {
                keywords.add(city);
                Logger.d(TAG, "city:" + city);
            }
        }
        return keywords;
    }

    public void clearHistory(){
        SPCache.put(BaseApp.mContext, KEY_WORD, "");
    }
}
