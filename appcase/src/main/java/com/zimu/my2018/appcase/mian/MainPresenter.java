package com.zimu.my2018.appcase.mian;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView ;

    @Inject
    public MainPresenter() {

    }

    @Override
    public void attachView(MainContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
