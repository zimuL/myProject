package com.zimu.my2018.quyouapi.data.scenic.scenicsearch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/11/5
 */
public class ScenicHistorySearchData implements Serializable {

    private int type;
    private List<SearchListData> searh;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SearchListData> getSearh() {
        if (searh == null) {
            return new ArrayList<>();
        }
        return searh;
    }

    public void setSearh(List<SearchListData> searh) {
        this.searh = searh;
    }
}
