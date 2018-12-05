package com.zimu.my2018.quyouapi.data.scenic.scenicnear;

import java.io.Serializable;
import java.util.List;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/9
 */
public class ScenicDetailNearByData implements Serializable {

    private int type;
    private List<NearByscenicsBean> nearByscenics;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<NearByscenicsBean> getNearByscenics() {
        return nearByscenics;
    }

    public void setNearByscenics(List<NearByscenicsBean> nearByscenics) {
        this.nearByscenics = nearByscenics;
    }
}
