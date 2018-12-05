package com.zimu.my2018.appcase.search.searchscenic.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zimu.my2018.appcase.R;
import com.zimu.my2018.quyouapi.data.scenic.scenicsearch.SearchListData;
import com.zimu.my2018.quyoulib.utils.StringUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/11/5
 */
public class ScenicHistorySearchAdapter extends TagAdapter<SearchListData> {

    private Context mContext;
    private List<SearchListData> datas;

    public ScenicHistorySearchAdapter(Context context, List<SearchListData> datas) {
        super(datas);
        mContext = context;
        this.datas = datas;
    }

    @Override
    public View getView(FlowLayout parent, int position, SearchListData searchListData) {
        TextView textView = (TextView) LayoutInflater.from(mContext)
                .inflate(R.layout.item_search_tag, null, false);
        String scenicName = searchListData.getScenicName();
        if (StringUtils.checkNullPoint(scenicName)) {
            textView.setText(scenicName);
        }
        return textView;
    }
}
