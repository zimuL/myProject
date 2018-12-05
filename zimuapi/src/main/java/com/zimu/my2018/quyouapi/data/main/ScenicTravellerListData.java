package com.zimu.my2018.quyouapi.data.main;

import java.io.Serializable;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/11
 */
public class ScenicTravellerListData implements Serializable{
    private String headerUrl;
    private String name;
    private String sex;
    private int age;
    private int classType;
    private String brief;

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

    public String getBrief() {
        return brief == null ? "" : brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
