package cn.itsite.order;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.BaseApp;
import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author： Administrator on 2018/2/1 0001.
 * Email： liujia95me@126.com
 */

public class OrderListAdapter extends BaseRecyclerViewAdapter<String,BaseViewHolder>{

    public OrderListAdapter() {
        super(R.layout.item_order_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(BaseApp.mContext,LinearLayoutManager.HORIZONTAL,false));
        List<String> data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        OrderItemImageAdapter adapter = new OrderItemImageAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setNewData(data);
    }

    class OrderItemImageAdapter extends BaseRecyclerViewAdapter<String,BaseViewHolder>{

        public OrderItemImageAdapter() {
            super(R.layout.item_item_goods_image);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }
}