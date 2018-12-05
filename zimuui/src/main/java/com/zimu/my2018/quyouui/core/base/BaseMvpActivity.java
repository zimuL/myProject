package com.zimu.my2018.quyouui.core.base;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/25
 */
public abstract class BaseMvpActivity extends BaseTitleActivity {
    @Override
    protected void initAppPresenter() {
        super.initAppPresenter();
        initPresenter();
    }

    protected abstract void initPresenter();

    @Override
    protected void destroyAppPresenter() {
        super.destroyAppPresenter();
        destroyPresenter();
    }

    protected abstract void destroyPresenter();
}

