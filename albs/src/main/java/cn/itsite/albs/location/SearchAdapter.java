package cn.itsite.albs.location;

import android.text.TextUtils;

import com.amap.api.services.help.Tip;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;
import cn.itsite.albs.R;

/**
 * @author leguang
 * @version v0.0.0
 * @E-mail langmanleguang@qq.com
 * @time 2018/2/26 0026 19:25
 */

public class SearchAdapter extends BaseRecyclerViewAdapter<Tip, BaseViewHolder> {

    public SearchAdapter() {
        super(R.layout.item_poi);
    }

    @Override
    protected void convert(BaseViewHolder holder, Tip item) {
        String address = "";
        if (!TextUtils.isEmpty(item.getDistrict())) {
            address += item.getDistrict();
        }

        if (!TextUtils.isEmpty(item.getAddress())) {
            address += item.getAddress();
        }

        holder.setText(R.id.tv_title_item_poi, item.getName())
                .setText(R.id.tv_description_item_poi, address)
                .setVisible(R.id.iv_clear_item_poi, "-1".equals(item.getTypeCode()))
                .addOnClickListener(R.id.iv_clear_item_poi);
    }

    public void removeAllHistory() {
        mData.clear();
        notifyDataSetChanged();
    }
}
