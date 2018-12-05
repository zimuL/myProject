package com.zimu.my2018.xiaoliuquyou.core.constants;

import com.zimu.my2018.xiaoliuquyou.app.BaseApplication;

import java.io.File;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/20
 */
public class Constants {
    public static final boolean Debug = true;
    public static final String PREFNAME = "qy_prefs";

    public static final String PATH_SDCARD_CACHE = BaseApplication.getInstance().getExternalCacheDir().getAbsolutePath();
    public static final String PATH_SDCARD_CACHE_IMG = PATH_SDCARD_CACHE + File.separator + "img";
}
