package cn.itsite.ec;

import android.os.Bundle;

import cn.itsite.abase.mvp.view.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_main_activity, MainFragment.newInstance());
        }
    }
}
