package com.zimu.my2018.appcase.scenic.scenicfootprint;

import android.content.Context;
import android.content.Intent;

import com.zimu.my2018.appcase.R;
import com.zimu.my2018.quyouui.core.base.BaseTitleActivity;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：scenic footprint
 * Created by hxl on 2018/10/27
 */
public class ScenicFootprintActivity extends BaseTitleActivity implements ScenicFootprintContract.View{

    @Inject
    ScenicFootprintPresenter mPresenter;

    public static Intent getDiyIntent(Context context) {
        Intent intent = new Intent(context, ScenicFootprintActivity.class);
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
    protected int getAppView() {
        return R.layout.activity_scenic_footprint_view;
    }

    @Override
    protected void initView() {
        setTitle("足迹");
    }

    @Override
    protected void loadData() {
        showContent(true);
    }
}
