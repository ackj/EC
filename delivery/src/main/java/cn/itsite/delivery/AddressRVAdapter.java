package cn.itsite.delivery;

import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author： Administrator on 2018/1/31 0031.
 * Email： liujia95me@126.com
 */

public class AddressRVAdapter extends BaseRecyclerViewAdapter<AddressBean, BaseViewHolder> {

    public AddressRVAdapter() {
        super(R.layout.item_address);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressBean item) {
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_phone, item.getPhoneNumber())
                .setText(R.id.tv_address, item.getLocation() + item.getAddress());
    }
}
