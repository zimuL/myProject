package com.zimu.my2018.appcase.mian.fragment_main.fragment.fragment_footprint;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/10
 */
public class FootPrintChoosePresenter implements FootPrintChooseContract.Presenter {

    private FootPrintChooseContract.View mView;

    @Inject
    public FootPrintChoosePresenter() {

    }

    @Override
    public void attachView(FootPrintChooseContract.View view) {
        mView= view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
