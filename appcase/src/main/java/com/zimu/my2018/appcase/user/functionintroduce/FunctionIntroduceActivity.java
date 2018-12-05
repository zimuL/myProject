package com.zimu.my2018.appcase.user.functionintroduce;

import android.content.Context;
import android.content.Intent;

import com.zimu.my2018.appcase.R;
import com.zimu.my2018.quyouui.core.base.BaseTitleActivity;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：function introduce
 * Created by hxl on 2018/10/27
 */
public class FunctionIntroduceActivity extends BaseTitleActivity implements FunctionIntroduceContract.View{

    @Inject
    FunctionIntroducePresenter mPresenter;

    public static Intent getDiyIntent(Context context) {
        Intent intent = new Intent(context, FunctionIntroduceActivity.class);
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
        return R.layout.activity_funcation_introduce_view;
    }

    @Override
    protected void initView() {
        setTitle("功能介绍");
    }

    @Override
    protected void loadData() {
        showContent(true);
    }
}
