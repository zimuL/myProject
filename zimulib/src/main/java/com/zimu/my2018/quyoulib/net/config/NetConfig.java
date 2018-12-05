package com.zimu.my2018.quyoulib.net.config;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/20
 */
public class NetConfig {
    private static String BASE_URL = "";
    private static Boolean DEBUG;

    public static void init(String url, Boolean debug) {
        BASE_URL = url;
        DEBUG = debug;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static Boolean getDEBUG() {
        return DEBUG;
    }
}
