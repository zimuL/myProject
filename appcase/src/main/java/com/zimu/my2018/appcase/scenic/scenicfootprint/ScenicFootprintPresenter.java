package com.zimu.my2018.appcase.scenic.scenicfootprint;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/27
 */
public class ScenicFootprintPresenter implements ScenicFootprintContract.Presenter{

    private ScenicFootprintContract.View mView ;

    @Inject
    public ScenicFootprintPresenter() {

    }

    @Override
    public void attachView(ScenicFootprintContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
