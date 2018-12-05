package com.zimu.my2018.appcase.scenic.scenicattentionlist;

import com.zimu.my2018.quyouapi.data.scenic.scenicattention.ScenicAttention;
import com.zimu.my2018.quyouapi.data.scenic.scenicattention.ScenicAttentionData;
import com.zimu.my2018.quyoulib.core.di.mvp.BasePresenter;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/26
 */
public class ScenicAttentionListContract {

    interface View {
        void onGetScenicAttentionListDataSuccess(ScenicAttentionData response);

        void onGetScenicAttentionListDataFailed(String msg);

        void onCancelScenicAttentionSuccess(ScenicAttention response);

        void onCancelScenicAttentionFailed(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void getScenicAttentionListData();

        void cancelScenicAttention(int scenic_id);
    }
}
