package com.zimu.my2018.quyouapi.data.scenic.scenicdetail;

import com.zimu.my2018.quyouapi.data.main.scenics.ArrayAudioBean;
import com.zimu.my2018.quyouapi.data.main.scenics.ArrayImageBean;
import com.zimu.my2018.quyouapi.data.main.scenics.ArrayTextBean;
import com.zimu.my2018.quyouapi.data.main.scenics.ArrayVideoBean;
import com.zimu.my2018.quyouapi.data.main.scenics.ScenicBean;

import java.io.Serializable;
import java.util.List;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/23
 */
public class ScenicDetailData implements Serializable {

    private ScenicBean scenic;
    private int type;
    private boolean follow;
    private List<ArrayImageBean> arrayImage;
    private List<ArrayVideoBean> arrayVideo;
    private List<ArrayTextBean> arrayText;
    private List<ServicePointsBean> servicePoints;
    private List<ArrayAudioBean> arrayAudio;

    public ScenicBean getScenic() {
        return scenic;
    }

    public void setScenic(ScenicBean scenic) {
        this.scenic = scenic;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }

    public List<ArrayImageBean> getArrayImage() {
        return arrayImage;
    }

    public void setArrayImage(List<ArrayImageBean> arrayImage) {
        this.arrayImage = arrayImage;
    }

    public List<ArrayVideoBean> getArrayVideo() {
        return arrayVideo;
    }

    public void setArrayVideo(List<ArrayVideoBean> arrayVideo) {
        this.arrayVideo = arrayVideo;
    }

    public List<ArrayTextBean> getArrayText() {
        return arrayText;
    }

    public void setArrayText(List<ArrayTextBean> arrayText) {
        this.arrayText = arrayText;
    }

    public List<ServicePointsBean> getServicePoints() {
        return servicePoints;
    }

    public void setServicePoints(List<ServicePointsBean> servicePoints) {
        this.servicePoints = servicePoints;
    }

    public List<ArrayAudioBean> getArrayAudio() {
        return arrayAudio;
    }

    public void setArrayAudio(List<ArrayAudioBean> arrayAudio) {
        this.arrayAudio = arrayAudio;
    }

}
