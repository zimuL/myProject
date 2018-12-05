package com.zimu.my2018.appcase.scenic.scenicattentionlist;

import com.zimu.my2018.appmodel.repository.ScenicRepository;
import com.zimu.my2018.quyouapi.core.netcallback.RequestCallback;
import com.zimu.my2018.quyouapi.data.scenic.scenicattention.ScenicAttention;
import com.zimu.my2018.quyouapi.data.scenic.scenicattention.ScenicAttentionData;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/26
 */
public class ScenicAttentionListPresenter implements ScenicAttentionListContract.Presenter {

    @Inject
    ScenicRepository mRepository;

    private ScenicAttentionListContract.View mView;

    @Inject
    public ScenicAttentionListPresenter() {

    }

    @Override
    public void attachView(ScenicAttentionListContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    /**
     * scenic attention list
     */
    @Override
    public void getScenicAttentionListData() {
        mRepository.getScenicAttentionListData(new RequestCallback<ScenicAttentionData>() {
            @Override
            public void onSuccess(ScenicAttentionData response) {
                if (null != mView) {
                    mView.onGetScenicAttentionListDataSuccess(response);
                }
            }

            @Override
            public void onError(String msg) {
                if (null != mView) {
                    mView.onGetScenicAttentionListDataFailed(msg);
                }
            }
        });
    }

    @Override
    public void cancelScenicAttention(int scenic_id) {
        mRepository.cancelScenicAttention(scenic_id, new RequestCallback<ScenicAttention>() {
            @Override
            public void onSuccess(ScenicAttention response) {
                if (null != mView) {
                    mView.onCancelScenicAttentionSuccess(response);
                }
            }

            @Override
            public void onError(String msg) {
                if (null != mView) {
                    mView.onCancelScenicAttentionFailed(msg);
                }
            }
        });
    }
}
