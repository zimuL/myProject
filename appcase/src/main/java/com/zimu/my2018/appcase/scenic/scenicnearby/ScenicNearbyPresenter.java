package com.zimu.my2018.appcase.scenic.scenicnearby;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/24
 */
public class ScenicNearbyPresenter implements ScenicNearbyContract.Presenter {

    private ScenicNearbyContract.View mView;

    @Inject
    public ScenicNearbyPresenter() {

    }

    @Override
    public void attachView(ScenicNearbyContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
