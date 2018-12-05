package com.zimu.my2018.quyouapi.core.api;

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

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/20
 */
public interface ApiScenicService {

    /**
     * scenic list
     */
    @POST("pageSearchByDefault.do")
    @FormUrlEncoded
    Observable<ScenicListData> getScenicList(
            @Field("user_id") int user_id,
            @Field("start_index") int start_index,
            @Field("end_index") int end_index,
            @Field("lng") int lng,
            @Field("lat") int lat,
            @Field("pageType") int pageType,
            @Field("scenictype") int scenictype);

    /**
     * scenic detail
     */
    @GET("getScenicMessageById.do")
    Observable<ScenicDetailData> getScenicDetailData(
            @Query("user_id") int user_id,
            @Query("scenic_id") int scenic_id
    );

    /**
     * scenic main banner
     */
    @GET("resource/weApp/banner_info/pics/info.json")
    Observable<MainBannerData> getBannerListData();

    /**
     * scenic near
     */
    @GET("getNearbyScenicsByLngLat.do")
    Observable<ScenicDetailNearByData> getScenicNearData(
            @Query("lng") String lng,
            @Query("lat") String lat
    );

    /**
     * scenic search
     */
    @POST("html/scenic/searchScenicDataByLikeName.do")
    @FormUrlEncoded
    Observable<ScenicSearchData> searchScenicData(
            @Field("scenic_like_name") String scenic_like_name
    );

    /**
     * scenic hot search
     */
    @GET("hotSear.do")
    Observable<ScenicHotSearchData> searchHotScenicListData(
    );

    /**
     * scenic history search
     */
    @GET("selectHistory.do")
    Observable<ScenicHistorySearchData> searchHistoryScenicListData(
            @Query("userId") int userId
    );

    /**
     * scenic add history search
     */
    @POST("insertSearchHistory.do")
    @FormUrlEncoded
    Observable<ScenicData> addSearchHistoryScenic(
            @Field("userId") int userId,
            @Field("scenicId") int scenicId,
            @Field("scenicName") String scenicName,
            @Field("time") String time
    );

    /**
     * scenic delete history search
     */
    @POST("deleteSearch.do")
    @FormUrlEncoded
    Observable<ScenicData> deleteSearchHistoryScenic(
            @Field("userId") int userId,
            @FieldMap Map<String, Object> map
            );

    /**
     * scenic attention
     */
    @POST("addScenicFocal.do")
    @FormUrlEncoded
    Observable<ScenicAttention> postScenicAttention(
            @Field("user_id") int user_id,
            @Field("scenic_id") int scenic_id
    );

    @POST("focal/removeScenicFocal.do")
    @FormUrlEncoded
    Observable<ScenicAttention> cancelScenicAttention(
            @Field("user_id") int user_id,
            @Field("scenic_id") int scenic_id
    );

    /**
     * scenic attention list
     */
    @GET("getScenicFocalByUserId.do")
    Observable<ScenicAttentionData> getScenicAttentionListData(
            @Query("user_id") int user_id
    );

    @POST("comment/saveScenicComment.do")
    @Multipart
    Observable<ScenicCommentData> addScenicComment(@PartMap Map<String, RequestBody> map,
                                                   @Part MultipartBody.Part[] files);

}
