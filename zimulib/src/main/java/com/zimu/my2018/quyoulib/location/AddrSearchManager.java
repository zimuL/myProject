package com.zimu.my2018.quyoulib.location;

import android.content.Context;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.util.List;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/24
 */
public class AddrSearchManager {
    private Context mContext;

    @Inject
    public AddrSearchManager(Context context) {
        this.mContext = context;
    }

    /**
     * 关键字检索POI
     */
    public void getSearch(String addr, String ctgr, String city, LatLonPoint mLatLonPoint, CallBack callBack) {
        // poi-1:构造 PoiSearch.Query 对象，通过 PoiSearch.Query(String query, String ctgr, String city) 设置搜索条件
        // 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
        // 第三个参数表示poi搜索区域(空字符串代表全国)
        PoiSearch.Query query = new PoiSearch.Query(addr, ctgr, city);
        query.setCityLimit(true);
        query.setPageSize(10);// 设置每页最多返回多少条poi item
        query.setPageNum(1);//设置查询页码
        // poi-2:构造 PoiSearch 对象，并设置监听。
        PoiSearch poiSearch = new PoiSearch(mContext, query);
        poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {

            @Override
            public void onPoiSearched(PoiResult poiResult, int i) {
                List<PoiItem> poiItemArrayList = poiResult.getPois();
                callBack.onSearched(poiItemArrayList);
            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {

            }
        });
        // 设置周边搜索的中心点以及半径
        poiSearch.setBound(new PoiSearch.SearchBound(mLatLonPoint, 1000, true));
        // poi-3:调用 PoiSearch 的 searchPOIAsyn() 方法发送请求。
        poiSearch.searchPOIAsyn();
    }

    /**
     * 周边检索POI
     */
    public void getBoundSearch(String name, double latitude, double longitude, CallBack callBack) {
        PoiSearch poiSearch = new PoiSearch(mContext, new PoiSearch.Query("", name, ""));
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(latitude, longitude), 1000));
        poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {

            @Override
            public void onPoiSearched(PoiResult poiResult, int i) {
                List<PoiItem> poiItemArrayList = poiResult.getPois();
                callBack.onSearched(poiItemArrayList);
            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {

            }
        });
        poiSearch.searchPOIAsyn();
    }


    public interface CallBack {
        void onSearched(List<PoiItem> poiItemList);
    }
}
