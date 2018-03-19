package cn.itsite.acommon;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Author： Administrator on 2018/2/22 0022.
 * Email： liujia95me@126.com
 * 商品计数控件
 */
public class GoodsCounterView extends LinearLayout {

    private TextView mTvCount;
    private ImageView mIvMinus;
    private ImageView mIvAdd;
    private int counter;

    public GoodsCounterView(Context context) {
        this(context,null);
    }

    public GoodsCounterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GoodsCounterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_goods_counter, null);
        mTvCount = view.findViewById(R.id.tv_count);
        mIvMinus = view.findViewById(R.id.iv_minus);
        mIvAdd = view.findViewById(R.id.iv_add);
        addView(view);
        initListener();
    }

    private void initListener() {
        mIvAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAdd();
//                listener.clickAdd(GoodsCounterView.this);
            }
        });
        mIvMinus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMinus();
//                listener.clickMinus(GoodsCounterView.this);
            }
        });
    }

    public void setCountWidth(int widthPx){
        LinearLayout.LayoutParams layoutParams = (LayoutParams) mTvCount.getLayoutParams();
        layoutParams.width = widthPx;
        mTvCount.setLayoutParams(layoutParams);
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
        mTvCount.setText(formatCounter(counter));
    }

    public void clickMinus() {
        if (counter > 0) {
            counter--;
            mTvCount.setText(formatCounter(counter));
        }
    }

    public void clickAdd() {
        counter++;
        mTvCount.setText(formatCounter(counter));
    }

    private String formatCounter(int counter) {
        String strCounter = String.valueOf(counter);
        if (counter > 99) {
            strCounter = "99+";
        }
        return strCounter;
    }

//    public void setOnAddMinusClickListener(OnAddMinusClickListener listener){
//        this.listener = listener;
//    }
//
//    private OnAddMinusClickListener listener;
//
//    public interface OnAddMinusClickListener{
//        void clickAdd(GoodsCounterView view);//加
//        void clickMinus(GoodsCounterView view);//减
//    }
}
