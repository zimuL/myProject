package com.zimu.my2018.quyouapi.data.main.banner;

import java.io.Serializable;
import java.util.List;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/11/6
 */
public class MainBannerData implements Serializable {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * type : miniProgram
         * name : 小溜时光
         * url : images/xiaoLiuTime.png
         * appid : wxb02b037821834f7c
         * apppath : pages/home/index/index
         * id :
         */

        private String type;
        private String name;
        private String url;
        private String appid;
        private String apppath;
        private String id;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getApppath() {
            return apppath;
        }

        public void setApppath(String apppath) {
            this.apppath = apppath;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
