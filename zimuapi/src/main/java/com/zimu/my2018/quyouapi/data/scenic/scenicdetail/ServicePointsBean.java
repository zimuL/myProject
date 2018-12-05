package com.zimu.my2018.quyouapi.data.scenic.scenicdetail;

/**
 * 功能：
 * 描述：scenic points
 * Created by hxl on 2018/10/23
 */
public class ServicePointsBean {
    /**
     * service_type : 1
     * resource_url : resource/area/xiKouZhen/scenics/xiKouZhen/services/meiShiJie/media/image/HZHC_a5EFlioBcDMJrh2tL1qI.jpg
     * lng : 119178236
     * service_name : 美食街
     * service_id : 798
     * lat : 28843718
     */

    private int service_type;
    private String resource_url;
    private int lng;
    private String service_name;
    private int service_id;
    private int lat;

    public int getService_type() {
        return service_type;
    }

    public void setService_type(int service_type) {
        this.service_type = service_type;
    }

    public String getResource_url() {
        return resource_url;
    }

    public void setResource_url(String resource_url) {
        this.resource_url = resource_url;
    }

    public int getLng() {
        return lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }
}
