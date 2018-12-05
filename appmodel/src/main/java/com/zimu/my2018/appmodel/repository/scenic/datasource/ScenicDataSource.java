package com.zimu.my2018.appmodel.repository.scenic.datasource;

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
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;


/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/20
 */
public class ScenicDataSource {
    public interface Local {

    }

    public interface Remote {
        /**
         * scenic lists
         */
        Observable<ScenicListData> getScenicList(int user_id, int start_index, int end_index, int lng, int lat, int pageType,
                                                 int scenictype);

        /**
         * scenic detail
         */
        Observable<ScenicDetailData> getScenicDetailData(int user_id, int scenic_id);

        /**
         * scenic main banner
         */
        Observable<MainBannerData> getBannerListData();

        /**
         * scenic near
         */
        Observable<ScenicDetailNearByData> getScenicNearData(String lng, String lat);

        /**
         * scenic search
         */
        Observable<ScenicSearchData> searchScenicData(String scenicName);

        /**
         * Scenic hot search
         */
        Observable<ScenicHotSearchData> searchHotScenicListData();

        /**
         * Scenic history search
         */
        Observable<ScenicHistorySearchData> searchHistoryScenicListData(int userId);

        /**
         * add search history
         */
        Observable<ScenicData> addSearchHistoryScenic(int userId, int scenicId, String scenicName, String time);

        /**
         * delete search history
         */
        Observable<ScenicData> deleteSearchHistoryScenic(int user_id, Map<String, Object> map);

        /**
         * scenic attention
         */
        Observable<ScenicAttention> postScenicAttention(int user_id, int scenic_id);

        Observable<ScenicAttention> cancelScenicAttention(int user_id, int scenic_id);

        /**
         * scenic attention list
         */
        Observable<ScenicAttentionData> getScenicAttentionListData(int user_id);

        /**
         * scenic comment
         */
        Observable<ScenicCommentData> addScenicComment(int userId, int scenic_id, float score, String comment_content, List<File> files);

    }
}
