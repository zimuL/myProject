package com.zimu.my2018.appcase.search.searchscenic;

import com.zimu.my2018.quyouapi.data.scenic.scenicsearch.ScenicData;
import com.zimu.my2018.quyouapi.data.scenic.scenicsearch.ScenicHistorySearchData;
import com.zimu.my2018.quyouapi.data.scenic.scenicsearch.ScenicHotSearchData;
import com.zimu.my2018.quyouapi.data.scenic.scenicsearch.ScenicSearchData;
import com.zimu.my2018.quyoulib.core.di.mvp.BasePresenter;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/24
 */
public class SearchScenicContract {
    interface View {
        void onSearchScenicDataSuccess(ScenicSearchData response);

        void onSearchScenicDataFailed(String msg);

        void onSearchHotScenicListDataSuccess(ScenicHotSearchData response);

        void onSearchHotScenicListDataFailed(String msg);

        void onSearchHistoryScenicListDataSuccess(ScenicHistorySearchData response);

        void onSearchHistoryScenicListDataFailed(String msg);

        void onAddSearchHistoryScenicSuccess(ScenicData response);

        void onAddSearchHistoryScenicFailed(String msg);

        void onDeleteSearchHistoryScenicSuccess(ScenicData response);

        void onDeleteSearchHistoryScenicFailed(String msg);
    }

    interface Presenter extends BasePresenter<View> {

        // 搜索景区
        void searchScenicData(String scenicName);

        // 查询热门搜索
        void searchHotScenicListData();

        // 查询历史搜索记录
        void searchHistoryScenicListData();

        // 新增历史搜索记录
        void addSearchHistoryScenic(int scenicId, String scenicName, String time);

        // 删除历史记录
        void deleteSearchHistoryScenic(int scenicId);
    }
}
