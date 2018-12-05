package com.zimu.my2018.appcase.scenic.scenicdetail;

import com.zimu.my2018.appmodel.repository.ScenicRepository;
import com.zimu.my2018.quyouapi.core.netcallback.RequestCallback;
import com.zimu.my2018.quyouapi.data.scenic.scenicattention.ScenicAttention;
import com.zimu.my2018.quyouapi.data.scenic.scenicdetail.ScenicDetailData;
import com.zimu.my2018.quyouapi.data.scenic.scenicnear.ScenicDetailNearByData;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/8
 */
public class ScenicDetailPresenter implements ScenicDetailContract.Presenter {

    @Inject
    ScenicRepository mScenicRepository ;

    private ScenicDetailContract.View mView;

    @Inject
    public ScenicDetailPresenter() {

    }


    @Override
    public void attachView(ScenicDetailContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void getScenicDetailData(int scenicId) {
        mScenicRepository.getScenicDetailData(scenicId, new RequestCallback<ScenicDetailData>() {
            @Override
            public void onSuccess(ScenicDetailData response) {
                if (null != mView) {
                    mView.onGetScenicDetailDataSuccess(response);
                }
            }

            @Override
            public void onError(String msg) {
                if (null != mView) {
                    mView.onGetScenicDetailDataFailed(msg);
                }
            }
        });
    }

    @Override
    public void getScenicNearData(String lng, String lat) {
        mScenicRepository.getScenicNearData(lng, lat, new RequestCallback<ScenicDetailNearByData>() {
            @Override
            public void onSuccess(ScenicDetailNearByData response) {
                if (null != mView) {
                    mView.onGetScenicNearDataSuccess(response);
                }
            }

            @Override
            public void onError(String msg) {
                if (null != mView) {
                    mView.onGetScenicNearDataFailed(msg);
                }
            }
        });
    }

    /**
     * scenic attention
     */
    @Override
    public void postScenicAttention(int scenic_id) {
        mScenicRepository.postScenicAttention(scenic_id, new RequestCallback<ScenicAttention>() {
            @Override
            public void onSuccess(ScenicAttention response) {
                if (null != mView) {
                    mView.onPostScenicAttentionSuccess(response);
                }
            }

            @Override
            public void onError(String msg) {
                if (null != mView) {
                    mView.onPostScenicAttentionFailed(msg);
                }
            }
        });
    }

    @Override
    public void cancelScenicAttention(int scenic_id) {
        mScenicRepository.cancelScenicAttention(scenic_id, new RequestCallback<ScenicAttention>() {
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
