package com.zimu.my2018.appcase.mian.fragment_main;

import com.zimu.my2018.quyouapi.data.main.banner.MainBannerData;
import com.zimu.my2018.quyoulib.core.di.mvp.BasePresenter;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
public class MainFragContract {

    public interface View {
        void onGetBannerListDataSuccess(MainBannerData response);

        void onGetBannerListDataFailed(String msg);
    }

    public interface Presenter extends BasePresenter<MainFragContract.View> {
        void getBannerListData();
    }
}
