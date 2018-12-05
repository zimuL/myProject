package com.zimu.my2018.appcase.mian.fragment_main;

import com.zimu.my2018.appmodel.repository.ScenicRepository;
import com.zimu.my2018.quyouapi.core.netcallback.RequestCallback;
import com.zimu.my2018.quyouapi.data.main.banner.MainBannerData;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
public class MainFragPresenter implements MainFragContract.Presenter {

    @Inject
    ScenicRepository mScenicRepository;

    private MainFragContract.View mView;

    @Inject
    public MainFragPresenter() {

    }

    @Override
    public void attachView(MainFragContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    // main banner
    @Override
    public void getBannerListData() {
        mScenicRepository.getBannerListData(new RequestCallback<MainBannerData>() {
            @Override
            public void onSuccess(MainBannerData response) {
                if (null != mView) {
                    mView.onGetBannerListDataSuccess(response);
                }
            }

            @Override
            public void onError(String msg) {
                if (null != mView) {
                    mView.onGetBannerListDataFailed(msg);
                }
            }
        });
    }
}
