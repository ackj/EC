package cn.itsite.login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Author： Administrator on 2018/3/7 0007.
 * Email： liujia95me@126.com
 */

public class LoginVPAdapter extends FragmentPagerAdapter {

    public LoginVPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return LoginInputFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
