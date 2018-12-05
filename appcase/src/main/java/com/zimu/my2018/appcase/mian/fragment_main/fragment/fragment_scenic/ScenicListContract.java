package com.zimu.my2018.appcase.mian.fragment_main.fragment.fragment_scenic;

import com.zimu.my2018.quyouapi.data.main.scenics.ScenicListData;
import com.zimu.my2018.quyoulib.core.di.mvp.BasePresenter;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/9
 */
public class ScenicListContract {
    public interface View{
        void onGetScenicListSuccess(ScenicListData response);

        void onGetScenicListFailed(String msg);
    }

    public interface Presenter extends BasePresenter<ScenicListContract.View> {
        void getScenicList(int start_index, int end_index, int lng, int lat, int pageType,
                           int scenictype);
    }
}
