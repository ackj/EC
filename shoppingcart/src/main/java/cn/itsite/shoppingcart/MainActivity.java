package cn.itsite.shoppingcart;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.itsite.abase.mvp.view.base.BaseActivity;

@Route(path="/shopcart/mainactivity")
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            loadRootFragment(android.R.id.content, ShoppingCartFragment.newInstance());
        }
    }
}
