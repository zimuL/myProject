package com.zimu.my2018.quyouapi.data.main.scenics;

import java.io.Serializable;
import java.util.List;

/**
 * 功能：
 * 描述：景区
 * Created by hxl on 2018/10/20
 */
public class ScenicsBean implements Serializable{
    private String distanceByUser;
    private int inferScenicFocal;
    private HeatTemperatureBean heatTemperature;
    private ScenicBean scenic;
    private List<ArrayImageBean> arrayImage;
    private List<ArrayVideoBean> arrayVideo;
    private List<ArrayTextBean> arrayText;
    private List<ArrayAudioBean> arrayAudio;

    public String getDistanceByUser() {
        return distanceByUser;
    }

    public void setDistanceByUser(String distanceByUser) {
        this.distanceByUser = distanceByUser;
    }

    public int getInferScenicFocal() {
        return inferScenicFocal;
    }

    public void setInferScenicFocal(int inferScenicFocal) {
        this.inferScenicFocal = inferScenicFocal;
    }

    public HeatTemperatureBean getHeatTemperature() {
        return heatTemperature;
    }

    public void setHeatTemperature(HeatTemperatureBean heatTemperature) {
        this.heatTemperature = heatTemperature;
    }

    public ScenicBean getScenic() {
        return scenic;
    }

    public void setScenic(ScenicBean scenic) {
        this.scenic = scenic;
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

    public List<ArrayAudioBean> getArrayAudio() {
        return arrayAudio;
    }

    public void setArrayAudio(List<ArrayAudioBean> arrayAudio) {
        this.arrayAudio = arrayAudio;
    }
}
