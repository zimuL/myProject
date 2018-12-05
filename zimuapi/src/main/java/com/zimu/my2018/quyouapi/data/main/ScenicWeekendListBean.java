package com.zimu.my2018.quyouapi.data.main;

import java.io.Serializable;

/**
 * Created by hxl on 2018/10/7 at haiChou.
 */
public class ScenicWeekendListBean implements Serializable {

    private String url;
    private String name;

    public String getUrl() {
        return url == null ? "" : url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
