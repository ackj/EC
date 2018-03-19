package cn.itsite.order;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Author： Administrator on 2018/2/1 0001.
 * Email： liujia95me@126.com
 */

public class MineOrderVPAdapter extends FragmentPagerAdapter {

//    private String[] mTitles = BaseApp.mContext.getResources().getStringArray(R.array.mine_order_tabs);
    private List<CategoryBean> categories;

    public MineOrderVPAdapter(FragmentManager fm,List<CategoryBean> categories) {
        super(fm);
        this.categories = categories;
    }

    @Override
    public Fragment getItem(int position) {
        return OrderListFragment.newInstance();
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position).getCategory();
    }
}
