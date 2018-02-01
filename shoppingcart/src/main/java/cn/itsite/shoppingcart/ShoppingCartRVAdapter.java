package cn.itsite.shoppingcart;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author： Administrator on 2018/1/31 0031.
 * Email： liujia95me@126.com
 */

public class ShoppingCartRVAdapter extends BaseMultiItemQuickAdapter<ShoppingCartBean,BaseViewHolder> {

    public ShoppingCartRVAdapter() {
        super(null);
        addItemType(ShoppingCartBean.TYPE_STORE_TITLE,R.layout.item_store_title);
        addItemType(ShoppingCartBean.TYPE_STORE_GOODS,R.layout.item_store_goods);
        addItemType(ShoppingCartBean.TYPE_RECOMMEND_TITLE,R.layout.item_recommend_title);
        addItemType(ShoppingCartBean.TYPE_RECOMMEND_GOODS,R.layout.item_grid_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShoppingCartBean item) {

    }
}
