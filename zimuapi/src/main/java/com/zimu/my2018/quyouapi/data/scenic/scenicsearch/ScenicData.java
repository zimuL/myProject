package com.zimu.my2018.quyouapi.data.scenic.scenicsearch;

import java.io.Serializable;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/11/5
 */
public class ScenicData implements Serializable {

    private String type;

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
