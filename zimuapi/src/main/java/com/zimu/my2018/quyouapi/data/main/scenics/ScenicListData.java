package com.zimu.my2018.quyouapi.data.main.scenics;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hxl on 2018/10/7 .
 * Scenic lists
 */
public class ScenicListData implements Serializable {

    private String serverDomain;
    private int type;
    private String down;
    private List<ScenicsBean> scenics;

    public String getServerDomain() {
        return serverDomain;
    }

    public void setServerDomain(String serverDomain) {
        this.serverDomain = serverDomain;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDown() {
        return down;
    }

    public void setDown(String down) {
        this.down = down;
    }

    public List<ScenicsBean> getScenics() {
        return scenics;
    }

    public void setScenics(List<ScenicsBean> scenics) {
        this.scenics = scenics;
    }
}
