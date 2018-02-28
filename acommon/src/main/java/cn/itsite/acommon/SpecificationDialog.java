package cn.itsite.acommon;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.utils.DensityUtils;
import cn.itsite.adialog.dialogfragment.BaseDialogFragment;

/**
 * Author： Administrator on 2018/2/7 0007.
 * Email： liujia95me@126.com
 */

public class SpecificationDialog extends BaseDialogFragment {

    private RecyclerView mRecyclerView;
    private GoodsCounterView mTvGoodsCounter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_specification,null);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mTvGoodsCounter = view.findViewById(R.id.view_goods_counter);
        return view;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setGravity(Gravity.BOTTOM);
        setAnimStyle(R.anim.slide_enter);
        initData();
    }

    private void initData() {
        mTvGoodsCounter.setCountWidth(DensityUtils.dp2px(getContext(),65));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SpecificationRVAdapter mAdapter = new SpecificationRVAdapter();
        mRecyclerView.setAdapter(mAdapter);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < Math.random() * 3; j++) {
                sb.append("哈哈哈");
            }
            data.add(sb.toString());
        }
        mAdapter.setNewData(data);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
