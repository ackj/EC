package cn.itsite.goodshome;

import android.os.Bundle;

import cn.itsite.abase.mvp.view.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            loadRootFragment(android.R.id.content, StoreHomeFragment.newInstance());
        }
    }
}
