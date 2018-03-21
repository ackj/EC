package cn.itsite.goodssearch;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.utils.ScreenUtils;
import cn.itsite.acommon.Params;
import cn.itsite.goodssearch.contract.KeywordsContract;
import cn.itsite.goodssearch.presenter.KeywordsPresenter;

/**
 * Author： Administrator on 2018/1/30 0030.
 * Email： liujia95me@126.com
 */
@Route(path = "/goodssearch/searchgoodsfragment")
public class SearchGoodsFragment extends BaseFragment<KeywordsPresenter> implements View.OnClickListener, KeywordsContract.View {

    private static final String TAG = SearchGoodsFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private SearchGoodsRVAdapter mSearchGoodsAdapter;
    private LinearLayout mLlToolbar;
    private TextView mTvSearch;
    private EditText mEtInput;
    private ImageView mIvBack;
    private List<SearchGoodsBean> mHotKeywordsDatas;
    private List<SearchGoodsBean> mKeywordsDatas;
    private List<SearchGoodsBean> mProductsDatas;

    private Params mParmas = new Params();

    public static SearchGoodsFragment newInstance() {
        return new SearchGoodsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    protected KeywordsPresenter createPresenter() {
        return new KeywordsPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_goods, container, false);
        mLlToolbar = view.findViewById(R.id.ll_toolbar);
        mTvSearch = view.findViewById(R.id.tv_search);
        mEtInput = view.findViewById(R.id.et_input);
        mIvBack = view.findViewById(R.id.iv_back);
        mRecyclerView = view.findViewById(R.id.recyclerView);
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
        mLlToolbar.setPadding(mLlToolbar.getPaddingLeft(), mLlToolbar.getPaddingTop() + ScreenUtils.getStatusBarHeight(_mActivity), mLlToolbar.getPaddingRight(), mLlToolbar.getPaddingBottom());
    }

    private void initData() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 6));
        mSearchGoodsAdapter = new SearchGoodsRVAdapter();
        mRecyclerView.setAdapter(mSearchGoodsAdapter);

        //获取热门搜索
        mPresenter.getKeywords(mParmas);
    }

    private void refreshData(final List<SearchGoodsBean> data) {
        mSearchGoodsAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return data.get(position).getSpanSize();
            }
        });
        mSearchGoodsAdapter.setNewData(data);
    }

    private void initListener() {
        mTvSearch.setOnClickListener(this);
        mEtInput.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mEtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mParmas.keyword = s.toString();
                mPresenter.getKeywords(mParmas);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSearchGoodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SearchGoodsBean item = mSearchGoodsAdapter.getItem(position);
                switch (item.getItemType()) {
                    case SearchGoodsBean.TYPE_HISTORY_TITLE:
                        break;
                    case SearchGoodsBean.TYPE_HISTORY_ITEM:
                    case SearchGoodsBean.TYPE_SEARCH_STRING:
                        SearchGoodsFragment.super.start("");
                        mParmas.keyword = item.getKeywordBean().getQuery();
                        mPresenter.getProducts(mParmas);
                        break;
                    case SearchGoodsBean.TYPE_SEARCH_GOODS:
                        Fragment goodsDetailFragment = (Fragment) ARouter.getInstance().build("/goodsdetail/goodsdetailfragment").navigation();
                        start((BaseFragment) goodsDetailFragment);
                        break;
                    default:

                }
            }
        });

        mEtInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    search();
                }
                return false;
            }
        });
    }

    private void search(){
        String input = mEtInput.getText().toString();
        if (!TextUtils.isEmpty(input)) {
            super.start("");
            mParmas.keyword = input;
            mPresenter.getProducts(mParmas);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            refreshData(mHotKeywordsDatas);
        } else if (v.getId() == R.id.et_input) {
//            refreshData(data2);
        } else if (v.getId() == R.id.tv_search) {
            search();
        }
    }

    @Override
    public void start(Object response) {
    }

    @Override
    public void responseGetKeywords(List<KeywordBean> datas) {
        mKeywordsDatas = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            SearchGoodsBean keywordBean = new SearchGoodsBean();
            keywordBean.setItemType(SearchGoodsBean.TYPE_SEARCH_STRING);
            keywordBean.setSpanSize(6);
            keywordBean.setKeywordBean(datas.get(i));
            mKeywordsDatas.add(keywordBean);
        }
        refreshData(mKeywordsDatas);
    }

    @Override
    public void responseGetHotKeywords(List<KeywordBean> datas) {
        mHotKeywordsDatas = new ArrayList<>();

        //从本地获取历史搜索记录，塞到list中
        List<String> keyword2Local = mPresenter.getKeyword2Local();
        if (keyword2Local.size() > 0) {
            SearchGoodsBean title = new SearchGoodsBean();
            title.setItemType(SearchGoodsBean.TYPE_HISTORY_TITLE);
            title.setTitle("历史记录");
            title.setSpanSize(6);
            mHotKeywordsDatas.add(title);
        }
        for (int i = 0; i < keyword2Local.size(); i++) {
            SearchGoodsBean keywordBean = new SearchGoodsBean();
            keywordBean.setItemType(SearchGoodsBean.TYPE_HISTORY_ITEM);
            keywordBean.setSpanSize(2);
            KeywordBean bean = new KeywordBean();
            bean.setQuery(keyword2Local.get(i));
            keywordBean.setKeywordBean(bean);
            mHotKeywordsDatas.add(keywordBean);
        }

        SearchGoodsBean title2 = new SearchGoodsBean();
        title2.setItemType(SearchGoodsBean.TYPE_HISTORY_TITLE);
        title2.setTitle("热门搜索");
        title2.setSpanSize(6);
        mHotKeywordsDatas.add(title2);
        for (int i = 0; i < datas.size(); i++) {
            SearchGoodsBean keywordBean = new SearchGoodsBean();
            keywordBean.setItemType(SearchGoodsBean.TYPE_HISTORY_ITEM);
            keywordBean.setSpanSize(2);
            keywordBean.setKeywordBean(datas.get(i));
            mHotKeywordsDatas.add(keywordBean);
        }
        refreshData(mHotKeywordsDatas);
    }

    @Override
    public void responseGetProducts(List<GoodsBean> data) {
        mProductsDatas = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            SearchGoodsBean product = new SearchGoodsBean();
            product.setSpanSize(3);
            product.setItemType(SearchGoodsBean.TYPE_SEARCH_GOODS);
            product.setGoodsBean(data.get(i));
            mProductsDatas.add(product);
        }
        refreshData(mProductsDatas);
    }
}
