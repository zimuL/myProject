package com.zimu.my2018.appcase.search.searchscenic;

import android.content.Context;

import com.zimu.my2018.appmodel.repository.ScenicRepository;
import com.zimu.my2018.quyouapi.core.netcallback.RequestCallback;
import com.zimu.my2018.quyouapi.data.scenic.scenicsearch.ScenicData;
import com.zimu.my2018.quyouapi.data.scenic.scenicsearch.ScenicHistorySearchData;
import com.zimu.my2018.quyouapi.data.scenic.scenicsearch.ScenicHotSearchData;
import com.zimu.my2018.quyouapi.data.scenic.scenicsearch.ScenicSearchData;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/24
 */
public class SearchScenicPresenter implements SearchScenicContract.Presenter {

    @Inject
    ScenicRepository mRepository;

    private Context mContext;
    private SearchScenicContract.View mView;

    @Inject
    public SearchScenicPresenter() {

    }

    @Override
    public void attachView(SearchScenicContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    /**
     * scenic search
     */
    @Override
    public void searchScenicData(String scenicName) {
        mRepository.searchScenicData(scenicName, new RequestCallback<ScenicSearchData>() {
            @Override
            public void onSuccess(ScenicSearchData response) {
                if (null != mView) {
                    mView.onSearchScenicDataSuccess(response);
                }
            }

            @Override
            public void onError(String msg) {
                if (null != mView) {
                    mView.onSearchScenicDataFailed(msg);
                }
            }
        });
    }

    /**
     * search hot scenic
     */
    @Override
    public void searchHotScenicListData() {
        mRepository.searchHotScenicListData(new RequestCallback<ScenicHotSearchData>() {
            @Override
            public void onSuccess(ScenicHotSearchData response) {
                if (null != mView) {
                    mView.onSearchHotScenicListDataSuccess(response);
                }
            }

            @Override
            public void onError(String msg) {
                if (null != mView) {
                    mView.onSearchHotScenicListDataFailed(msg);
                }
            }
        });
    }

    /**
     * search history scenic
     */
    @Override
    public void searchHistoryScenicListData() {
        mRepository.searchHistoryScenicListData(new RequestCallback<ScenicHistorySearchData>() {
            @Override
            public void onSuccess(ScenicHistorySearchData response) {
                if (null != mView) {
                    mView.onSearchHistoryScenicListDataSuccess(response);
                }
            }

            @Override
            public void onError(String msg) {
                if (null != mView) {
                    mView.onSearchHistoryScenicListDataFailed(msg);
                }
            }
        });
    }

    /**
     * add search history
     */
    @Override
    public void addSearchHistoryScenic(int scenicId, String scenicName, String time) {
        mRepository.addSearchHistoryScenic(scenicId, scenicName, time, new RequestCallback<ScenicData>() {
            @Override
            public void onSuccess(ScenicData response) {
                if (null != mView) {
                    mView.onAddSearchHistoryScenicSuccess(response);
                }
            }

            @Override
            public void onError(String msg) {
                if (null != mView) {
                    mView.onAddSearchHistoryScenicFailed(msg);
                }
            }
        });
    }

    /**
     * delete search history
     */
    @Override
    public void deleteSearchHistoryScenic(int scenicId) {
        mRepository.deleteSearchHistoryScenic(scenicId, new RequestCallback<ScenicData>() {
            @Override
            public void onSuccess(ScenicData response) {
                if (null != mView) {
                    mView.onDeleteSearchHistoryScenicSuccess(response);
                }
            }

            @Override
            public void onError(String msg) {
                if (null != mView) {
                    mView.onAddSearchHistoryScenicFailed(msg);
                }
            }
        });
    }
}
