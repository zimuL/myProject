package com.zimu.my2018.appcase.scenic.scenicnearby.adapter;

import android.util.Log;

import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.IndoorData;
import com.amap.api.services.poisearch.SubPoiItem;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zimu.my2018.appcase.R;

import java.util.List;
import java.util.Locale;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/25
 */
public class ScenicNearbyAdapter extends BaseQuickAdapter<PoiItem, BaseViewHolder>{

    public ScenicNearbyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PoiItem item) {
        String title = item.getTitle();
        int distance = item.getDistance();
        String typeDes = item.getTypeDes();
        String snippet = item.getSnippet();
        helper.setText(R.id.tv_nearby_name, title);
        helper.setText(R.id.tv_nearby_type, typeDes);
        helper.setText(R.id.tv_nearby_distance, String.format(Locale.getDefault(), "%d米", distance));
        helper.setText(R.id.tv_nearby_address, snippet);
    }
}
