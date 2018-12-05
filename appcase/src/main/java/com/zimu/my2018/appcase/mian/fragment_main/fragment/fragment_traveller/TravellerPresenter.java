package com.zimu.my2018.appcase.mian.fragment_main.fragment.fragment_traveller;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/10
 */
public class TravellerPresenter implements TravellerContract.Presenter {

    private TravellerContract.View mView;

    @Inject
    public TravellerPresenter() {

    }

    @Override
    public void attachView(TravellerContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
