package cn.itsite.shoppingcart;

import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.itsite.acommon.GoodsCounterView;

/**
 * Author： Administrator on 2018/1/31 0031.
 * Email： liujia95me@126.com
 */

public class ShoppingCartRVAdapter extends BaseMultiItemQuickAdapter<StoreBean, BaseViewHolder> {

    public ShoppingCartRVAdapter() {
        super(null);
        addItemType(StoreBean.TYPE_STORE_TITLE, R.layout.item_store_title);
        addItemType(StoreBean.TYPE_STORE_GOODS, R.layout.item_store_goods);
        addItemType(StoreBean.TYPE_RECOMMEND_TITLE, R.layout.item_recommend_title);
        addItemType(StoreBean.TYPE_RECOMMEND_GOODS, R.layout.item_grid_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreBean item) {
        switch (item.getItemType()) {
            case StoreBean.TYPE_STORE_TITLE:
                helper
                        .setText(R.id.tv_store_name, item.getShopBean().getName())
                        .setOnCheckedChangeListener(R.id.checkBox, null)
                        .setChecked(R.id.checkBox, item.isChecked())
                        .setOnCheckedChangeListener(R.id.checkBox, new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                listener.onStoreCheckedChanged(helper.getLayoutPosition(), isChecked);
                            }
                        });
                break;
            case StoreBean.TYPE_STORE_GOODS:
                GoodsCounterView goodsCounterView = helper.getView(R.id.view_goodscounter);
                goodsCounterView.setCounter(item.getProductsBean().getCount());
                helper.setOnCheckedChangeListener(R.id.checkBox, null)
                        .setText(R.id.tv_name, item.getProductsBean().getTitle())
                        .setText(R.id.tv_specification, item.getProductsBean().getDescription())
                        .setChecked(R.id.checkBox, item.isChecked())
                        .setOnCheckedChangeListener(R.id.checkBox, new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                listener.onGoodsCheckedChanged(helper.getLayoutPosition(), isChecked);
                            }
                        })
                        .addOnClickListener(R.id.tv_specification)
                        .addOnClickListener(R.id.tv_confirm)
                        .setText(R.id.tv_name, item.getProductsBean().getTitle())
                        .setText(R.id.tv_desc, item.getProductsBean().getDescription())
                        .setText(R.id.tv_price, item.getProductsBean().getPay().getCurrency() + " " + item.getProductsBean().getPay().getCost());

                ImageView ivIcon = helper.getView(R.id.iv_icon);
                Glide.with(ivIcon.getContext())
                        .load(item.getProductsBean().getIcon())
                        .into(ivIcon);
                break;
            case StoreBean.TYPE_RECOMMEND_TITLE:
                break;
            case StoreBean.TYPE_RECOMMEND_GOODS:
                ImageView mIvIcon = helper.getView(R.id.iv_icon);
                Glide.with(mIvIcon.getContext())
                        .load(item.getRecommendGoodsBean().getImageUrl())
                        .into(mIvIcon);
                helper.addOnClickListener(R.id.cl_goods_layout)
                        .setText(R.id.tv_name, item.getRecommendGoodsBean().getTitle())
                        .setText(R.id.tv_desc, item.getRecommendGoodsBean().getDescription())
                        .setText(R.id.tv_price, item.getRecommendGoodsBean().getCurrency() + " " + item.getRecommendGoodsBean().getPrice());
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

//    private GoodsCounterView.OnAddMinusClickListener addMinusClickListener;
//
//    public void setOnAddMinusClickListener(GoodsCounterView.OnAddMinusClickListener listener) {
//        addMinusClickListener = listener;
//    }

}
