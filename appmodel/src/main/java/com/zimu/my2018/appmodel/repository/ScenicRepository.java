package com.zimu.my2018.appmodel.repository;

import com.zimu.my2018.appmodel.base.BaseRepository;
import com.zimu.my2018.appmodel.repository.scenic.datasource.local.ScenicLocalDataSource;
import com.zimu.my2018.appmodel.repository.scenic.datasource.remote.ScenicRemoteDataSource;
import com.zimu.my2018.quyouapi.core.netcallback.RequestCallback;
import com.zimu.my2018.quyouapi.data.main.banner.MainBannerData;
import com.zimu.my2018.quyouapi.data.main.scenics.ScenicListData;
import com.zimu.my2018.quyouapi.data.scenic.ScenicCommentData;
import com.zimu.my2018.quyouapi.data.scenic.scenicattention.ScenicAttention;
import com.zimu.my2018.quyouapi.data.scenic.scenicattention.ScenicAttentionData;
import com.zimu.my2018.quyouapi.data.scenic.scenicdetail.ScenicDetailData;
import com.zimu.my2018.quyouapi.data.scenic.scenicnear.ScenicDetailNearByData;
import com.zimu.my2018.quyouapi.data.scenic.scenicsearch.ScenicData;
import com.zimu.my2018.quyouapi.data.scenic.scenicsearch.ScenicHistorySearchData;
import com.zimu.my2018.quyouapi.data.scenic.scenicsearch.ScenicHotSearchData;
import com.zimu.my2018.quyouapi.data.scenic.scenicsearch.ScenicSearchData;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/20
 */
@Singleton
public class ScenicRepository extends BaseRepository {

    @Inject
    ScenicRemoteDataSource mScenicRemoteDataSource ;
    @Inject
    ScenicLocalDataSource mScenicLocalDataSource ;

    @Inject
    public ScenicRepository() {

    }

    /**
     * scenic list
     */
    public void getScenicList(int start_index, int end_index, int lng, int lat, int pageType,
                              int scenictype, final RequestCallback<ScenicListData> requestCallback) {
        int user_id = prefManager.getInt("userId", 0);
        mScenicRemoteDataSource.getScenicList(user_id, start_index,end_index, lng, lat, pageType, scenictype)
                .subscribe(new Observer<ScenicListData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScenicListData response) {
                        requestCallback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * scenic detail
     */
    public void getScenicDetailData(int scenicId, final RequestCallback<ScenicDetailData> requestCallback) {
        int user_id = prefManager.getInt("userId", 0);
        mScenicRemoteDataSource.getScenicDetailData(user_id, scenicId)
                .subscribe(new Observer<ScenicDetailData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScenicDetailData response) {
                        requestCallback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * scenic main banner
     */
    public void getBannerListData(final RequestCallback<MainBannerData> requestCallback) {
        mScenicRemoteDataSource.getBannerListData()
                .subscribe(new Observer<MainBannerData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MainBannerData response) {
                        requestCallback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * scenic near
     */
    public void getScenicNearData(String lng, String lat, final RequestCallback<ScenicDetailNearByData> requestCallback) {
        mScenicRemoteDataSource.getScenicNearData(lng, lat)
                .subscribe(new Observer<ScenicDetailNearByData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScenicDetailNearByData response) {
                        requestCallback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * scenic search
     */
    public void searchScenicData(String scenicName, final RequestCallback<ScenicSearchData> requestCallback) {
        mScenicRemoteDataSource.searchScenicData(scenicName)
                .subscribe(new Observer<ScenicSearchData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScenicSearchData response) {
                        requestCallback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * Scenic hot search
     */
    public void searchHotScenicListData(final RequestCallback<ScenicHotSearchData> requestCallback) {
        mScenicRemoteDataSource.searchHotScenicListData()
                .subscribe(new Observer<ScenicHotSearchData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScenicHotSearchData response) {
                        requestCallback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * Scenic history search
     */
    public void searchHistoryScenicListData(final RequestCallback<ScenicHistorySearchData> requestCallback) {
        int user_id = prefManager.getInt("userId", 0);
        mScenicRemoteDataSource.searchHistoryScenicListData(user_id)
                .subscribe(new Observer<ScenicHistorySearchData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScenicHistorySearchData response) {
                        requestCallback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * add search history
     */
    public void addSearchHistoryScenic(int scenicId, String scenicName, String time, final RequestCallback<ScenicData> requestCallback) {
        int user_id = prefManager.getInt("userId", 0);
        mScenicRemoteDataSource.addSearchHistoryScenic(user_id, scenicId, scenicName, time)
                .subscribe(new Observer<ScenicData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScenicData response) {
                        requestCallback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * delete search history
     */
    public void deleteSearchHistoryScenic(int scenicId, final RequestCallback<ScenicData> requestCallback) {
        int user_id = prefManager.getInt("userId", 0);

        Map<String, Object> map = new HashMap<>() ;

        if (0 != scenicId) {
            map.put("scenicId", scenicId);
        }

        mScenicRemoteDataSource.deleteSearchHistoryScenic(user_id, map)
                .subscribe(new Observer<ScenicData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScenicData response) {
                        requestCallback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * scenic attention
     */
    public void postScenicAttention(int scenic_id, final RequestCallback<ScenicAttention> requestCallback) {
        int user_id = prefManager.getInt("userId", 0);
        mScenicRemoteDataSource.postScenicAttention(user_id, scenic_id)
                .subscribe(new Observer<ScenicAttention>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScenicAttention response) {
                        requestCallback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void cancelScenicAttention(int scenic_id, final RequestCallback<ScenicAttention> requestCallback) {
        int user_id = prefManager.getInt("userId", 0);
        mScenicRemoteDataSource.cancelScenicAttention(user_id, scenic_id)
                .subscribe(new Observer<ScenicAttention>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScenicAttention response) {
                        requestCallback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * scenic attention list
     */
    public void getScenicAttentionListData(final RequestCallback<ScenicAttentionData> requestCallback) {
        int user_id = prefManager.getInt("userId", 0);
        mScenicRemoteDataSource.getScenicAttentionListData(user_id)
                .subscribe(new Observer<ScenicAttentionData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScenicAttentionData response) {
                        requestCallback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * scenic comment
     */
    public void addScenicComment(int scenic_id, float score, String comment_content, List<File> files, final RequestCallback<ScenicCommentData> requestCallback) {
        int user_id = prefManager.getInt("userId", 0);
        mScenicRemoteDataSource.addScenicComment(user_id, scenic_id, score, comment_content, files)
                .subscribe(new Observer<ScenicCommentData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScenicCommentData response) {
                        requestCallback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
