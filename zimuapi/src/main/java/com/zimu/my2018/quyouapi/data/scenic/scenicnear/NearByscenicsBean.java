package com.zimu.my2018.quyouapi.data.scenic.scenicnear;

import com.zimu.my2018.quyouapi.data.main.scenics.ScenicBean;

import java.io.Serializable;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/24
 */
public class NearByscenicsBean implements Serializable {

    private String distanceByUser;
    private ScenicBean scenic;

    public String getDistanceByUser() {
        return distanceByUser;
    }

    public void setDistanceByUser(String distanceByUser) {
        this.distanceByUser = distanceByUser;
    }

    public ScenicBean getScenic() {
        return scenic;
    }

    public void setScenic(ScenicBean scenic) {
        this.scenic = scenic;
    }
}
