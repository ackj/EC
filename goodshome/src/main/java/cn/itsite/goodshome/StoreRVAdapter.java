package cn.itsite.goodshome;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author： Administrator on 2018/1/30 0030.
 * Email： liujia95me@126.com
 */

public class StoreRVAdapter extends BaseMultiItemQuickAdapter<StoreItemGridBean, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     */
    public StoreRVAdapter() {
        super(null);
        addItemType(StoreItemGridBean.TYPE_MORE, R.layout.item_goods_more);
        addItemType(StoreItemGridBean.TYPE_RECOMMEND, R.layout.item_goods_recommend);
        addItemType(StoreItemGridBean.TYPE_GOODS, R.layout.item_grid_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, final StoreItemGridBean item) {
        switch (item.getItemType()) {
            case StoreItemGridBean.TYPE_MORE:
                break;
            case StoreItemGridBean.TYPE_RECOMMEND:
                break;
            case StoreItemGridBean.TYPE_GOODS:
                break;
            default:
        }
    }
}
