package com.zimu.my2018.appcase.mian.fragment_main.fragment.fragment_scenic;

import com.zimu.my2018.appmodel.repository.ScenicRepository;
import com.zimu.my2018.quyouapi.core.netcallback.RequestCallback;
import com.zimu.my2018.quyouapi.data.main.scenics.ScenicListData;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/9
 */
public class ScenicListPresenter implements ScenicListContract.Presenter {

    @Inject
    ScenicRepository mScenicRepository ;

    private ScenicListContract.View mView;

    @Inject
    public ScenicListPresenter() {

    }

    @Override
    public void attachView(ScenicListContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void getScenicList(int start_index, int end_index, int lng, int lat, int pageType, int scenictype) {
        mScenicRepository.getScenicList(start_index, end_index, lng, lat, pageType, scenictype, new RequestCallback<ScenicListData>() {
            @Override
            public void onSuccess(ScenicListData response) {
                if (null != mView) {
                    mView.onGetScenicListSuccess(response);
                }
            }

            @Override
            public void onError(String msg) {
                if (null != mView) {
                    mView.onGetScenicListFailed(msg);
                }
            }
        });
    }
}
