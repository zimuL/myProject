package com.zimu.my2018.quyouapi.data.scenic.scenicattention;

import java.io.Serializable;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/26
 */
public class ScenicFocalBean implements Serializable {
    private int scenic_id;
    private int user_id;
    private long focus_time;

    public int getScenic_id() {
        return scenic_id;
    }

    public void setScenic_id(int scenic_id) {
        this.scenic_id = scenic_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public long getFocus_time() {
        return focus_time;
    }

    public void setFocus_time(long focus_time) {
        this.focus_time = focus_time;
    }
}
