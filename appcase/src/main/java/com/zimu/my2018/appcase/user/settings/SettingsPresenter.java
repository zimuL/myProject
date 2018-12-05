package com.zimu.my2018.appcase.user.settings;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/27
 */
public class SettingsPresenter implements SettingsContract.Presenter {

    private SettingsContract.View mView;

    @Inject
    public SettingsPresenter() {

    }

    @Override
    public void attachView(SettingsContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
