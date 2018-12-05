package com.zimu.my2018.quyouapi.data.scenic.scenicsearch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/11/3
 */
public class ScenicHotSearchData implements Serializable{

    private int type;
    private List<SearchListData> hotSearh;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SearchListData> getHotSearh() {
        if (hotSearh == null) {
            return new ArrayList<>();
        }
        return hotSearh;
    }

    public void setHotSearh(List<SearchListData> hotSearh) {
        this.hotSearh = hotSearh;
    }
}
