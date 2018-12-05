package com.zimu.my2018.quyouui.widget.data.photo;

import java.io.Serializable;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/25
 */
public class NetPhotoData implements Serializable {
    public final static int UPLOAD_SUCCESS = 1;
    public final static int UPLOAD_ING = 2;
    public final static int UPLOAD_FAILED = 3;

    private String localPath = null; // 本地路径
    private String netPath = null;//网络路径
    private String netPathSmall = null; //网络小图
    private String picDes = "";// 图片描述
    private int uploadStatus = UPLOAD_SUCCESS;
    private int height;
    private int width;

    public NetPhotoData() {
    }

    public NetPhotoData(String netPath) {
        this.netPath = netPath;
    }

    public NetPhotoData(String netPath, String picDes) {
        this.netPath = netPath;
        this.picDes = picDes;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getNetPath() {
        return netPath;
    }

    public void setNetPath(String netPath) {
        this.netPath = netPath;
    }

    public String getNetPathSmall() {
        return netPathSmall == null ? "" : netPathSmall;
    }

    public void setNetPathSmall(String netPathSmall) {
        this.netPathSmall = netPathSmall;
    }

    public String getPicDes() {
        return picDes;
    }

    public void setPicDes(String picDes) {
        this.picDes = picDes;
    }

    public int getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(int uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}

