package com.zimu.my2018.appmodel.repository.scenic.datasource.remote;

import com.zimu.my2018.appmodel.repository.scenic.datasource.ScenicDataSource;
import com.zimu.my2018.quyouapi.core.api.ApiScenicService;
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
import com.zimu.my2018.quyoulib.net.transform.TransformUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/20
 */
public class ScenicRemoteDataSource implements ScenicDataSource.Remote {

    @Inject
    ApiScenicService mApiScenicService;

    @Inject
    public ScenicRemoteDataSource() {

    }

    /**
     * scenic list
     */
    @Override
    public Observable<ScenicListData> getScenicList(int user_id, int start_index, int end_index, int lng, int lat,
                                                    int pageType, int scenictype) {
        ObservableTransformer<ScenicListData, ScenicListData> transformer = TransformUtils.defaultSchedulers();
        Observable<ScenicListData> compose = mApiScenicService.getScenicList(user_id, start_index, end_index,
                lng, lat, pageType, scenictype)
                .compose(transformer);
        return compose;
    }

    /**
     * scenic detail
     */
    @Override
    public Observable<ScenicDetailData> getScenicDetailData(int user_id, int scenic_id) {
        ObservableTransformer<ScenicDetailData, ScenicDetailData> transformer = TransformUtils.defaultSchedulers();
        Observable<ScenicDetailData> compose = mApiScenicService.getScenicDetailData(user_id, scenic_id)
                .compose(transformer);
        return compose;
    }

    /**
     * scenic main banner
     */
    @Override
    public Observable<MainBannerData> getBannerListData() {
        ObservableTransformer<MainBannerData, MainBannerData> transformer = TransformUtils.defaultSchedulers();
        Observable<MainBannerData> compose = mApiScenicService.getBannerListData()
                .compose(transformer);
        return compose;
    }

    /**
     * scenic near
     */
    @Override
    public Observable<ScenicDetailNearByData> getScenicNearData(String lng, String lat) {
        ObservableTransformer<ScenicDetailNearByData, ScenicDetailNearByData> transformer = TransformUtils.defaultSchedulers();
        Observable<ScenicDetailNearByData> compose = mApiScenicService.getScenicNearData(lng, lat)
                .compose(transformer);
        return compose;
    }

    /**
     * scenic search
     */
    @Override
    public Observable<ScenicSearchData> searchScenicData(String scenicName) {
        ObservableTransformer<ScenicSearchData, ScenicSearchData> transformer = TransformUtils.defaultSchedulers();
        Observable<ScenicSearchData> compose = mApiScenicService.searchScenicData(scenicName)
                .compose(transformer);
        return compose;
    }

    /**
     * Scenic hot search
     */
    @Override
    public Observable<ScenicHotSearchData> searchHotScenicListData() {
        ObservableTransformer<ScenicHotSearchData, ScenicHotSearchData> transformer = TransformUtils.defaultSchedulers();
        Observable<ScenicHotSearchData> compose = mApiScenicService.searchHotScenicListData()
                .compose(transformer);
        return compose;
    }

    /**
     * Scenic history search
     */
    @Override
    public Observable<ScenicHistorySearchData> searchHistoryScenicListData(int userId) {
        ObservableTransformer<ScenicHistorySearchData, ScenicHistorySearchData> transformer = TransformUtils.defaultSchedulers();
        Observable<ScenicHistorySearchData> compose = mApiScenicService.searchHistoryScenicListData(userId)
                .compose(transformer);
        return compose;
    }

    /**
     * add search history
     */
    @Override
    public Observable<ScenicData> addSearchHistoryScenic(int userId, int scenicId, String scenicName, String time) {
        ObservableTransformer<ScenicData, ScenicData> transformer = TransformUtils.defaultSchedulers();
        Observable<ScenicData> compose = mApiScenicService.addSearchHistoryScenic(userId, scenicId, scenicName, time)
                .compose(transformer);
        return compose;
    }

    /**
     * delete search history
     */
    @Override
    public Observable<ScenicData> deleteSearchHistoryScenic(int user_id, Map<String, Object> map) {
        ObservableTransformer<ScenicData, ScenicData> transformer = TransformUtils.defaultSchedulers();
        Observable<ScenicData> compose = mApiScenicService.deleteSearchHistoryScenic(user_id, map)
                .compose(transformer);
        return compose;
    }

    /**
     * scenic attention
     */
    @Override
    public Observable<ScenicAttention> postScenicAttention(int user_id, int scenic_id) {
        ObservableTransformer<ScenicAttention, ScenicAttention> transformer = TransformUtils.defaultSchedulers();
        Observable<ScenicAttention> compose = mApiScenicService.postScenicAttention(user_id, scenic_id)
                .compose(transformer);
        return compose;
    }

    @Override
    public Observable<ScenicAttention> cancelScenicAttention(int user_id, int scenic_id) {
        ObservableTransformer<ScenicAttention, ScenicAttention> transformer = TransformUtils.defaultSchedulers();
        Observable<ScenicAttention> compose = mApiScenicService.cancelScenicAttention(user_id, scenic_id)
                .compose(transformer);
        return compose;
    }

    @Override
    public Observable<ScenicAttentionData> getScenicAttentionListData(int user_id) {
        ObservableTransformer<ScenicAttentionData, ScenicAttentionData> transformer = TransformUtils.defaultSchedulers();
        Observable<ScenicAttentionData> compose = mApiScenicService.getScenicAttentionListData(user_id)
                .compose(transformer);
        return compose;
    }

    @Override
    public Observable<ScenicCommentData> addScenicComment(int user_id, int scenic_id, float score, String comment_content, List<File> files) {
        Map<String, RequestBody> map = new HashMap<>() ;

        map.put("user_id",parseRequestBody(String.valueOf(user_id)));
        map.put("scenic_id",parseRequestBody(String.valueOf(scenic_id)));
        map.put("score",parseRequestBody(String.valueOf(score)));
        map.put("comment_content",parseRequestBody(comment_content));

        int size = files.size();
        MultipartBody.Part[] bodys = new MultipartBody.Part[size];
        if (files.size()>0) {
            for (int i=0;i<size;i++) {
                File file = files.get(i);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("files",file.getName(),requestBody);
                bodys[i] = body;
            }
        }

        ObservableTransformer<ScenicCommentData, ScenicCommentData> transformer = TransformUtils.defaultSchedulers();
        Observable<ScenicCommentData> compose = mApiScenicService.addScenicComment(map, bodys)
                .compose(transformer);
        return compose;
    }

    public static RequestBody parseRequestBody(String value) {
        return RequestBody.create(MediaType. parse("text/plain"), value);
    }
}
