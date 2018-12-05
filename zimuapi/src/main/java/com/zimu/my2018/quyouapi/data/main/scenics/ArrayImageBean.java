package com.zimu.my2018.quyouapi.data.main.scenics;

import java.io.Serializable;

/**
 * 功能：
 * 描述：Scenic image
 * Created by hxl on 2018/10/20
 */
public class ArrayImageBean implements Serializable{
    private String resource_url;
    private int scenic_id;
    private int resource_type;
    private int resource_id;
    private String resource_introduction;

    public String getResource_url() {
        return resource_url;
    }

    public void setResource_url(String resource_url) {
        this.resource_url = resource_url;
    }

    public int getScenic_id() {
        return scenic_id;
    }

    public void setScenic_id(int scenic_id) {
        this.scenic_id = scenic_id;
    }

    public int getResource_type() {
        return resource_type;
    }

    public void setResource_type(int resource_type) {
        this.resource_type = resource_type;
    }

    public int getResource_id() {
        return resource_id;
    }

    public void setResource_id(int resource_id) {
        this.resource_id = resource_id;
    }

    public String getResource_introduction() {
        return resource_introduction;
    }

    public void setResource_introduction(String resource_introduction) {
        this.resource_introduction = resource_introduction;
    }
}
