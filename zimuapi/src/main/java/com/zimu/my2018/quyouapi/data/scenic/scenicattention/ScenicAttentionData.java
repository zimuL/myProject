package com.zimu.my2018.quyouapi.data.scenic.scenicattention;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/26
 */
public class ScenicAttentionData implements Serializable {

    private int type;
    private List<ScenicAttentionListData> focalList;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<ScenicAttentionListData> getFocalList() {
        if (focalList == null) {
            return new ArrayList<>();
        }
        return focalList;
    }

    public void setFocalList(List<ScenicAttentionListData> focalList) {
        this.focalList = focalList;
    }
}
