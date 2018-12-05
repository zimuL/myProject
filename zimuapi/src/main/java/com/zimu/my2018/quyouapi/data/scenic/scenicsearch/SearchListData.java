package com.zimu.my2018.quyouapi.data.scenic.scenicsearch;

import java.io.Serializable;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/11/3
 */
public class SearchListData implements Serializable{

    private String scenicName;
    private int scenicId;
    private String time;
    private int userId;

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public int getScenicId() {
        return scenicId;
    }

    public void setScenicId(int scenicId) {
        this.scenicId = scenicId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
