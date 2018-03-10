package cn.itsite.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.itsite.abase.mvp.view.base.BaseFragment;

/**
 * Author： Administrator on 2018/3/7 0007.
 * Email： liujia95me@126.com
 */

public class LoginInputFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = LoginInputFragment.class.getSimpleName();
    private TextInputLayout mTilPhone;

    public static LoginInputFragment newInstance() {
        return new LoginInputFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_login, container, false);
        mTilPhone = view.findViewById(R.id.til_phone);
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
