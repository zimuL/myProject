package com.zimu.my2018.appcase.scenic.scenicbannerpicture;

import com.zimu.my2018.appmodel.repository.ScenicRepository;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/27
 */
public class ScenicBannerPicturePresenter implements ScenicBannerPictureContract.Presenter {

    private ScenicBannerPictureContract.View mView;

    @Inject
    ScenicRepository mScenicRepository;

    @Inject
    public ScenicBannerPicturePresenter() {

    }

    @Override
    public void attachView(ScenicBannerPictureContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

}
