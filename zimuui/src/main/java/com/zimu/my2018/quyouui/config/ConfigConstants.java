package com.zimu.my2018.quyouui.config;

import com.facebook.common.util.ByteConstants;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
public class ConfigConstants {
    public static final int MAX_DISK_CACHE_SIZE = 40 * ByteConstants.MB;
    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
    public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4;
}
