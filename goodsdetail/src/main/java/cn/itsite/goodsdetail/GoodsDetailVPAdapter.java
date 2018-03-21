package cn.itsite.goodsdetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author liujia
 * @version v0.0.0
 * @E-mail liujia95me@126.com
 * @time 2018/3/21 0021 14:36
 */

public class GoodsDetailVPAdapter extends FragmentPagerAdapter {


    public GoodsDetailVPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return position == 0?GoodsFragment.newInstance():DetailFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
