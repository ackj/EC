package cn.itsite.shoppingcart;

import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author： Administrator on 2018/1/31 0031.
 * Email： liujia95me@126.com
 */

public class ShoppingCartRVAdapter extends BaseMultiItemQuickAdapter<ShoppingCartGridBean, BaseViewHolder> {

    public ShoppingCartRVAdapter() {
        super(null);
        addItemType(ShoppingCartGridBean.TYPE_STORE_TITLE, R.layout.item_store_title);
        addItemType(ShoppingCartGridBean.TYPE_STORE_GOODS, R.layout.item_store_goods);
        addItemType(ShoppingCartGridBean.TYPE_RECOMMEND_TITLE, R.layout.item_recommend_title);
        addItemType(ShoppingCartGridBean.TYPE_RECOMMEND_GOODS, R.layout.item_grid_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShoppingCartGridBean item) {
        switch (item.getItemType()) {
            case ShoppingCartGridBean.TYPE_STORE_TITLE:
                helper
                        .setOnCheckedChangeListener(R.id.checkBox, null)
                        .setChecked(R.id.checkBox, item.isChecked())
                        .setOnCheckedChangeListener(R.id.checkBox, new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                listener.onStoreCheckedChanged(helper.getLayoutPosition(), isChecked);
                            }
                        });
                break;
            case ShoppingCartGridBean.TYPE_STORE_GOODS:
                helper.setOnCheckedChangeListener(R.id.checkBox, null)
                        .setChecked(R.id.checkBox, item.isChecked())
                        .setOnCheckedChangeListener(R.id.checkBox, new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                listener.onGoodsCheckedChanged(helper.getLayoutPosition(), isChecked);
                            }
                        })
                        .addOnClickListener(R.id.tv_specification)
                        .addOnClickListener(R.id.tv_confirm);
                break;
            case ShoppingCartGridBean.TYPE_RECOMMEND_TITLE:
                break;
            case ShoppingCartGridBean.TYPE_RECOMMEND_GOODS:
                helper.addOnClickListener(R.id.cl_goods_layout);
                break;
            default:
        }
    }

    private OnCheckedChangedListener listener;

    public void setOnCheckedChangedListener(OnCheckedChangedListener listener) {
        this.listener = listener;
    }

    public interface OnCheckedChangedListener {
        void onStoreCheckedChanged(int position, boolean isChecked);
        void onGoodsCheckedChanged(int position, boolean isChecked);

    }

}
