package cn.itsite.delivery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.itsite.abase.common.DialogHelper;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.ScreenUtils;
import cn.itsite.delivery.contract.AddDeliveryContract;
import cn.itsite.delivery.presenter.AddDeliveryPresenter;

/**
 * Author： Administrator on 2018/1/31 0031.
 * Email： liujia95me@126.com
 */
@Route(path="/delivery/addaddressfragment")
public class AddDeliveryFragment extends BaseFragment<AddDeliveryContract.Presenter> implements AddDeliveryContract.View {

    private static final String TAG = AddDeliveryFragment.class.getSimpleName();

    private RelativeLayout mRlToolbar;
    private TextView mTvAdd;

    private boolean isAdd;

    public static AddDeliveryFragment newInstance() {
        return new AddDeliveryFragment();
    }

    @NonNull
    @Override
    protected AddDeliveryContract.Presenter createPresenter() {
        return new AddDeliveryPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_address, container, false);
        mRlToolbar = view.findViewById(R.id.rl_toolbar);
        mTvAdd = view.findViewById(R.id.tv_add);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initStatusBar();
        initData();
        initListener();
    }

    private void initStatusBar() {
        mRlToolbar.setPadding(mRlToolbar.getPaddingLeft(), mRlToolbar.getPaddingTop() + ScreenUtils.getStatusBarHeight(_mActivity), mRlToolbar.getPaddingRight(), mRlToolbar.getPaddingBottom());
    }

    private void initData() {

    }

    private void initListener() {
        mTvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.postAddress();
            }
        });
    }

    @Override
    public void responsePostAddressSuccess() {
        DialogHelper.successSnackbar(getView(),"增加成功");
        pop();
    }

    @Override
    public void responsePutAddressSuccess() {
        DialogHelper.successSnackbar(getView(),"修改成功");
        pop();
    }
}
