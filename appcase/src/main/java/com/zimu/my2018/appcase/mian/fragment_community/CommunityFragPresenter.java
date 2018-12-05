package com.zimu.my2018.appcase.mian.fragment_community;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/11
 */
public class CommunityFragPresenter implements CommunityFragContract.Presenter{

    private CommunityFragContract.View mView;

    @Inject
    public CommunityFragPresenter() {

    }

    @Override
    public void attachView(CommunityFragContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
