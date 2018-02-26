package cn.itsite.goodsdetail;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;

import cn.itsite.abase.BaseApp;
import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author： Administrator on 2018/2/7 0007.
 * Email： liujia95me@126.com
 */

public class SpecificationRVAdapter extends BaseRecyclerViewAdapter<String, BaseViewHolder> {

    public SpecificationRVAdapter() {
        super(R.layout.item_specification);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        FlexboxLayout flexboxLayout = helper.getView(R.id.flexboxLayout);
        if (flexboxLayout.getChildCount() == 0) {
            for (int i = 0; i < Math.random() * 15; i++) {
                View view = LayoutInflater.from(BaseApp.mContext).inflate(R.layout.view_specification_text, null);
                TextView textView = view.findViewById(R.id.textView);
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < Math.random() * 4; j++) {
                    sb.append("哈哈");
                }
                if(i%4==0){
                    textView.setEnabled(false);
                }
                textView.setText(sb.toString());
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setSelected(!textView.isSelected());
                    }
                });
                flexboxLayout.addView(view);
            }
            flexboxLayout.post(new Runnable() {
                @Override
                public void run() {
                    notifyItemChanged(helper.getLayoutPosition());
                }
            });
        }
    }
}
