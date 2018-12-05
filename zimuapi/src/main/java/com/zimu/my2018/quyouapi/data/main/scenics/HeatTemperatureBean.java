package com.zimu.my2018.quyouapi.data.main.scenics;

/**
 * 功能：
 * 描述：景区热度
 * Created by hxl on 2018/10/20
 */
public class HeatTemperatureBean {
    private int scenic_id;
    private int entering_count;
    private int look_over_count;

    public int getScenic_id() {
        return scenic_id;
    }

    public void setScenic_id(int scenic_id) {
        this.scenic_id = scenic_id;
    }

    public int getEntering_count() {
        return entering_count;
    }

    public void setEntering_count(int entering_count) {
        this.entering_count = entering_count;
    }

    public int getLook_over_count() {
        return look_over_count;
    }

    public void setLook_over_count(int look_over_count) {
        this.look_over_count = look_over_count;
    }
}
