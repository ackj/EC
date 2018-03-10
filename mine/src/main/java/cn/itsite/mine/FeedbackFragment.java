package cn.itsite.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author： Administrator on 2018/3/9 0009.
 * Email： liujia95me@126.com
 */
@Route(path = "/mine/minefragment")
public class FeedbackFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = FeedbackFragment.class.getSimpleName();
    private TextInputLayout mTilPhone;

    public static FeedbackFragment newInstance() {
        return new FeedbackFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initStatusBar();
        initData();
        initListener();
    }

    private void initStatusBar() {
//        mLlToolbar.setPadding(mLlToolbar.getPaddingLeft(), mLlToolbar.getPaddingTop() + ScreenUtils.getStatusBarHeight(_mActivity), mLlToolbar.getPaddingRight(), mLlToolbar.getPaddingBottom());
    }

    private void initData() {
//        mTilPhone.setHint("请输入电话号码");
//        mTilPhone.setPasswordVisibilityToggleEnabled(true);
    }

    private void initListener() {
    }

    @Override
    public void onClick(View v) {

    }
}
