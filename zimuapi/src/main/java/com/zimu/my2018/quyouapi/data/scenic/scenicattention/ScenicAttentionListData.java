package com.zimu.my2018.quyouapi.data.scenic.scenicattention;

import com.zimu.my2018.quyouapi.data.main.scenics.ScenicBean;

import java.io.Serializable;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/26
 */
public class ScenicAttentionListData implements Serializable{
    private ScenicFocalBean scenicFocal;
    private ScenicBean scenic;

    public ScenicFocalBean getScenicFocal() {
        return scenicFocal;
    }

    public void setScenicFocal(ScenicFocalBean scenicFocal) {
        this.scenicFocal = scenicFocal;
    }

    public ScenicBean getScenic() {
        return scenic;
    }

    public void setScenic(ScenicBean scenic) {
        this.scenic = scenic;
    }
}
