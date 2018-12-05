package com.zimu.my2018.appcase.mian.fragment_mine;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/5
 */
public class MineFragPresenter implements MineFragContract.Presenter{

    private MineFragContract.View mView;

    @Inject
    public MineFragPresenter() {

    }

    @Override
    public void attachView(MineFragContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
