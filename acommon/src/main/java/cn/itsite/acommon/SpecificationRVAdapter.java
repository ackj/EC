package cn.itsite.acommon;

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

public class SpecificationRVAdapter extends BaseRecyclerViewAdapter<SkusBean.AttributesBean, BaseViewHolder> {

    public SpecificationRVAdapter() {
        super(R.layout.item_specification);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final SkusBean.AttributesBean item) {
        helper.setText(R.id.tv_attribute, item.getAttribute());
        final FlexboxLayout flexboxLayout = helper.getView(R.id.flexboxLayout);
        flexboxLayout.removeAllViews();
//        if (flexboxLayout.getChildCount() == 0) {
        for (int i = 0; i < item.getValues().size(); i++) {
            View view = LayoutInflater.from(BaseApp.mContext).inflate(R.layout.view_specification_text, null);
            final TextView textView = view.findViewById(R.id.textView);
            textView.setText(item.getValues().get(i).getValue());
            final SkusBean.AttributesBean.ValuesBean valuesBean = item.getValues().get(i);
            textView.setEnabled(item.getValues().get(i).isHasIntersection());
            textView.setSelected(valuesBean.isSelected());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击它的时候要做：
                    //1、遍历其他规格下的选项是否与选中的这一项有skuuid的交集
                    //2、如果没有，则该项不可点击，否则可点击
                    //3、刷新整个列表
                    textView.setSelected(!textView.isSelected());
                    valuesBean.setSelected(textView.isSelected());
                    if (textView.isSelected()) {
                        for (int j = 0; j < flexboxLayout.getChildCount(); j++) {
                            TextView tv = flexboxLayout.getChildAt(j).findViewById(R.id.textView);
                            if (textView != tv) {
                                tv.setSelected(false);
                                item.getValues().get(j).setSelected(false);
                            }
                        }
                    }
                    listener.onItemClick(valuesBean, helper.getLayoutPosition(), textView.isSelected(), flexboxLayout);
                }
            });
            flexboxLayout.addView(view);
        }
//            flexboxLayout.post(new Runnable() {
//                @Override
//                public void run() {
//                    notifyItemChanged(helper.getLayoutPosition());
//                }
//            });
//        }
    }

    private OnSpecificationClickListener listener;

    public void setOnSpecificationClickListener(OnSpecificationClickListener listener) {
        this.listener = listener;
    }

    public interface OnSpecificationClickListener {
        void onItemClick(SkusBean.AttributesBean.ValuesBean valuesBean, int position, boolean isSelete, FlexboxLayout flexboxLayout);
    }
}
