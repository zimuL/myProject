package com.zimu.my2018.quyouapi.data.main;

import java.io.Serializable;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/10
 */
public class ScenicFootPrintChooseListData implements Serializable{
    private String headerUrl;
    private String name;
    private String sex;
    private int age;
    private int classType;
    private String date;
    private double distance;
    private String contentUrl;
    private String brief;
    private int praise;
    private int share;
    private int message;

    public String getHeaderUrl() {
        return headerUrl == null ? "" : headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex == null ? "" : sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getClassType() {
        return classType;
    }

    public void setClassType(int classType) {
        this.classType = classType;
    }

    public String getDate() {
        return date == null ? "" : date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getContentUrl() {
        return contentUrl == null ? "" : contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getBrief() {
        return brief == null ? "" : brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }
}
