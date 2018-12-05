package com.zimu.my2018.appcase.user.functionintroduce;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/27
 */
public class FunctionIntroducePresenter implements  FunctionIntroduceContract.Presenter{

    private  FunctionIntroduceContract.View mView ;

    @Inject
    public  FunctionIntroducePresenter() {

    }

    @Override
    public void attachView(FunctionIntroduceContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
