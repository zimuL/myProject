package com.zimu.my2018.appcase.search.searchscenic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.appcase.R2;
import com.zimu.my2018.appcase.scenic.scenicdetail.ScenicDetailActivity;
import com.zimu.my2018.appcase.search.searchscenic.adapter.ScenicHistorySearchAdapter;
import com.zimu.my2018.appcase.search.searchscenic.adapter.ScenicHotSearchAdapter;
import com.zimu.my2018.appcase.search.searchscenic.adapter.ScenicSearchAdapter;
import com.zimu.my2018.quyouapi.data.main.scenics.ScenicBean;
import com.zimu.my2018.quyouapi.data.main.scenics.ScenicsBean;
import com.zimu.my2018.quyouapi.data.scenic.scenicsearch.ScenicData;
import com.zimu.my2018.quyouapi.data.scenic.scenicsearch.ScenicHistorySearchData;
import com.zimu.my2018.quyouapi.data.scenic.scenicsearch.ScenicHotSearchData;
import com.zimu.my2018.quyouapi.data.scenic.scenicsearch.ScenicSearchData;
import com.zimu.my2018.quyouapi.data.scenic.scenicsearch.SearchListData;
import com.zimu.my2018.quyoulib.utils.StringUtils;
import com.zimu.my2018.quyouui.core.base.BaseTitleActivity;
import com.zimu.my2018.quyouui.widget.dividerdecoration.DividerDecoration;
import com.zimu.my2018.utils.ViewUtil;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class SearchScenicActivity extends BaseTitleActivity implements SearchScenicContract.View,
        TextWatcher,
        BaseQuickAdapter.OnItemClickListener {

    @BindView(R2.id.et_search)
    EditText et_search;
    @BindView(R2.id.fl_search_hot)
    TagFlowLayout fl_search_hot;
    @BindView(R2.id.fl_search_history)
    TagFlowLayout fl_search_history;
    @BindView(R2.id.rv_scenic_search)
    RecyclerView rv_scenic_search;
    @BindView(R2.id.ll_search)
    LinearLayout ll_search;
    @BindView(R2.id.tv_delete)
    TextView tv_delete;

    @Inject
    SearchScenicPresenter mPresenter;

    private ScenicSearchAdapter mAdapter;
    private ScenicHotSearchAdapter mHotSearchAdapter;
    private ScenicHistorySearchAdapter mHistorySearchAdapter;

    public static Intent getDiyIntent(Context context) {
        Intent intent = new Intent(context, SearchScenicActivity.class);
        return intent;
    }

    @Override
    protected void initAppPresenter() {
        super.initAppPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void destroyAppPresenter() {
        super.destroyAppPresenter();
    }

    @Override
    protected int getAppView() {
        return R.layout.activity_search_scenic_view;
    }

    @Override
    protected void initView() {
        setTitle("搜索");
        initRecyclerView();
    }

    private void initRecyclerView() {
        DividerDecoration dividerDecoration = ViewUtil.getDividerDecoration(this, 1, 0, 0);
        ViewUtil.initRecycleView(this, rv_scenic_search, dividerDecoration);
        mAdapter = new ScenicSearchAdapter(R.layout.item_scenic_search_view) ;
        rv_scenic_search.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        et_search.addTextChangedListener(this);
        setViewListener(tv_delete, this);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        showContent(true);
        mPresenter.searchHotScenicListData();
        mPresenter.searchHistoryScenicListData();
    }

    private void search() {
        String name = et_search.getText().toString().trim();
        mPresenter.searchScenicData(name);
    }

    private void setData(ScenicSearchData response) {
        if (null != response && null != response.getScenics() && response.getScenics().size() > 0) {
            List<ScenicsBean> scenics = response.getScenics();
            mAdapter.setNewData(scenics);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String trim = et_search.getText().toString().trim();
        if (StringUtils.checkNullPoint(trim)){
            search();
        }else {
            rv_scenic_search.setVisibility(View.GONE);
            ll_search.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<ScenicsBean> data = mAdapter.getData();
        ScenicsBean scenicsBean = data.get(position);
        ScenicBean scenic = scenicsBean.getScenic();
        int scenic_id = scenic.getScenic_id();
        String scenic_name = scenic.getScenic_name();
        startActivity(ScenicDetailActivity.getDiyIntent(this, scenic_id));
        mPresenter.addSearchHistoryScenic(scenic_id, scenic_name, String.valueOf(new Date().getTime()));
    }

    @Override
    public void onViewClick(View view) {
        super.onViewClick(view);
        int id = view.getId();
        if (id == R.id.tv_delete) {
            mPresenter.deleteSearchHistoryScenic(0);
        }
    }

    // 热门搜索
    private void setHotScenicListData(ScenicHotSearchData response) {
        if (null == mHotSearchAdapter) {
            List<SearchListData> hotSearch = response.getHotSearh();
            if (null != hotSearch && hotSearch.size() > 0) {
                mHotSearchAdapter = new ScenicHotSearchAdapter(this, hotSearch);
                fl_search_hot.setAdapter(mHotSearchAdapter);
            }
        }
    }

    // 历史搜索
    private void setHistoryScenicListData(ScenicHistorySearchData response) {
        if (null == mHistorySearchAdapter) {
            List<SearchListData> search = response.getSearh();
            if (null != search && search.size() > 0) {
                mHistorySearchAdapter = new ScenicHistorySearchAdapter(this, search);
                fl_search_history.setAdapter(mHistorySearchAdapter);
            }
        }
    }

    @Override
    public void onSearchScenicDataSuccess(ScenicSearchData response) {
        rv_scenic_search.setVisibility(View.VISIBLE);
        ll_search.setVisibility(View.GONE);
        setData(response);
    }

    @Override
    public void onSearchScenicDataFailed(String msg) {
        showToast(msg);
    }

    @Override
    public void onSearchHotScenicListDataSuccess(ScenicHotSearchData response) {
        setHotScenicListData(response);
    }

    @Override
    public void onSearchHotScenicListDataFailed(String msg) {
        Log.e("hxl", "onSearchHotScenicListDataFailed: " + msg );
    }

    @Override
    public void onSearchHistoryScenicListDataSuccess(ScenicHistorySearchData response) {
        setHistoryScenicListData(response);
    }

    @Override
    public void onSearchHistoryScenicListDataFailed(String msg) {
        Log.e("hxl", "onSearchHistoryScenicListDataFailed: " + msg );
    }

    @Override
    public void onAddSearchHistoryScenicSuccess(ScenicData response) {
        String type = response.getType();
        if ("1".equals(type)) {
            Log.e("hxl", "onAddSearchHistoryScenicSuccess: " + "success" );
        }
    }

    @Override
    public void onAddSearchHistoryScenicFailed(String msg) {
        showToast(msg);
    }

    @Override
    public void onDeleteSearchHistoryScenicSuccess(ScenicData response) {
        if (null != response) {
            String type = response.getType();
            if ("1".equals(type)) {
                showToast("删除历史搜索成功！");
                mPresenter.searchHistoryScenicListData();
            }
        }
    }

    @Override
    public void onDeleteSearchHistoryScenicFailed(String msg) {
        showToast("删除失败！");
    }
}
