package cn.itsite.ec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.albs.location.LocationFragment;
import cn.itsite.classify.ClassifyFragment;
import cn.itsite.delivery.SelectShoppingAddressFragment;
import cn.itsite.goodshome.StoreHomeFragment;
import cn.itsite.goodssearch.SearchGoodsFragment;
import cn.itsite.login.LoginFragment;
import cn.itsite.mine.MineFragment;
import cn.itsite.order.MineOrderFragment;
import cn.itsite.order.OrderDetailFragment;
import cn.itsite.order.SubmitOrderFragment;
import cn.itsite.shoppingcart.ShoppingCartFragment;

/**
 * Author： Administrator on 2018/2/1 0001.
 * Email： liujia95me@126.com
 */

public class MainFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "MainFragment";

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
    }

    private void initListener() {
        getView().findViewById(R.id.btn_1).setOnClickListener(this);
        getView().findViewById(R.id.btn_2).setOnClickListener(this);
        getView().findViewById(R.id.btn_3).setOnClickListener(this);
        getView().findViewById(R.id.btn_4).setOnClickListener(this);
        getView().findViewById(R.id.btn_5).setOnClickListener(this);
        getView().findViewById(R.id.btn_6).setOnClickListener(this);
        getView().findViewById(R.id.btn_7).setOnClickListener(this);
        getView().findViewById(R.id.btn_8).setOnClickListener(this);
        getView().findViewById(R.id.btn_9).setOnClickListener(this);
        getView().findViewById(R.id.btn_10).setOnClickListener(this);
        getView().findViewById(R.id.btn_11).setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                start(StoreHomeFragment.newInstance());
                break;
            case R.id.btn_2:
                start(SearchGoodsFragment.newInstance());
                break;
            case R.id.btn_3:
                start(SelectShoppingAddressFragment.newInstance());
                break;
            case R.id.btn_4:
                start(ClassifyFragment.newInstance());
                break;
            case R.id.btn_5:
                start(ShoppingCartFragment.newInstance());
                break;
            case R.id.btn_6:
                start(SubmitOrderFragment.newInstance());
                break;
            case R.id.btn_7:
                start(MineOrderFragment.newInstance());
                break;
            case R.id.btn_8:
                start(OrderDetailFragment.newInstance());
                break;
            case R.id.btn_9:
                start(LoginFragment.newInstance());
                break;
            case R.id.btn_10:
                start(LocationFragment.newInstance());
                break;
            case R.id.btn_11:
                start(MineFragment.newInstance());

                net();
                break;
            default:
        }
    }

    public void net() {
//        HttpHelper.getService(KeywordService.class)
//                .getKeywords()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BasePresenter.BaseSubscriber<BaseResponse<List<KeywordBean>>>() {
//
//                    @Override
//                    public void onSuccess(BaseResponse<List<KeywordBean>> response) {
//
//
//                        Logger.e("111111111111" + response.getData().get(0).getQuery());
//                    }
//                });

    }


}
