package com.zimu.my2018.appcase.scenic.scenicevaluatelist;

import android.content.Context;
import android.content.Intent;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zimu.my2018.appcase.R;
import com.zimu.my2018.appcase.scenic.scenicevaluatelist.adapter.ScenicEvaluateListAdapter;
import com.zimu.my2018.quyouui.core.base.BaseRecycleViewActivity;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：scenic evaluate list
 * Created by hxl on 2018/10/27
 */
public class ScenicEvaluateListActivity extends BaseRecycleViewActivity implements
        ScenicEvaluateListContract.View{

    @Inject
    ScenicEvaluateListPresenter mPresenter;

    private ScenicEvaluateListAdapter mAdapter;

    public static Intent getDiyIntent(Context context) {
        Intent intent = new Intent(context, ScenicEvaluateListActivity.class);
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
        mPresenter.detachView();
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("景区评价");
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    protected BaseQuickAdapter getQuickAdapter() {
        mAdapter = new ScenicEvaluateListAdapter(R.layout.item_scenic_evaluate_list_view) ;
        return mAdapter;
    }

    @Override
    protected void loadData() {
        showContent(true);
    }
}
