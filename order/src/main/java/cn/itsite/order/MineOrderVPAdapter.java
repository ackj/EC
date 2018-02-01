package cn.itsite.order;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.itsite.abase.BaseApp;

/**
 * Author： Administrator on 2018/2/1 0001.
 * Email： liujia95me@126.com
 */

public class MineOrderVPAdapter extends FragmentPagerAdapter {

    private String[] mTitles = BaseApp.mContext.getResources().getStringArray(R.array.mine_order_tabs);

    public MineOrderVPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return OrderListFragment.newInstance();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
