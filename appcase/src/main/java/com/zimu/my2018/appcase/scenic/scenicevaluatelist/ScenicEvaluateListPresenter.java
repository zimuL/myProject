package com.zimu.my2018.appcase.scenic.scenicevaluatelist;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/27
 */
public class ScenicEvaluateListPresenter implements ScenicEvaluateListContract.Presenter {

    private ScenicEvaluateListContract.View mView;

    @Inject
    public ScenicEvaluateListPresenter() {

    }

    @Override
    public void attachView(ScenicEvaluateListContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
