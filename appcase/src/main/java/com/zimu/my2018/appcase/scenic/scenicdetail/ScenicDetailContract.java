package com.zimu.my2018.appcase.scenic.scenicdetail;

import com.zimu.my2018.quyouapi.data.scenic.scenicattention.ScenicAttention;
import com.zimu.my2018.quyouapi.data.scenic.scenicdetail.ScenicDetailData;
import com.zimu.my2018.quyouapi.data.scenic.scenicnear.ScenicDetailNearByData;
import com.zimu.my2018.quyoulib.core.di.mvp.BasePresenter;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/8
 */
public interface ScenicDetailContract {

    interface View{
        void onGetScenicDetailDataSuccess(ScenicDetailData response);

        void onGetScenicDetailDataFailed(String msg);

        void onGetScenicNearDataSuccess(ScenicDetailNearByData response);

        void onGetScenicNearDataFailed(String msg);

        void onPostScenicAttentionSuccess(ScenicAttention response);

        void onPostScenicAttentionFailed(String msg);

        void onCancelScenicAttentionSuccess(ScenicAttention response);

        void onCancelScenicAttentionFailed(String msg);
    }

    interface Presenter extends BasePresenter<ScenicDetailContract.View> {
        /**
         * Scenic detail
         */
        void getScenicDetailData(int scenicId);

        /**
         * Scenic near
         */
        void getScenicNearData(String lng, String lat);

        /**
         * Scenic attention
         */
        void postScenicAttention(int scenic_id);

        void cancelScenicAttention(int scenic_id);
    }
}
