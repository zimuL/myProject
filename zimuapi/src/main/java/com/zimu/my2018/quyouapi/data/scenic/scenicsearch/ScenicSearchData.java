package com.zimu.my2018.quyouapi.data.scenic.scenicsearch;

import com.zimu.my2018.quyouapi.data.main.scenics.ScenicsBean;

import java.io.Serializable;
import java.util.List;

/**
 * 功能：
 * 描述：scenic search
 * Created by hxl on 2018/10/24
 */
public class ScenicSearchData implements Serializable{

    private int type;
    private List<ScenicsBean> scenics;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<ScenicsBean> getScenics() {
        return scenics;
    }

    public void setScenics(List<ScenicsBean> scenics) {
        this.scenics = scenics;
    }
}
