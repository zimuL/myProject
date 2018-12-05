package com.zimu.my2018.quyoulib.pref_shared_preferences;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/20
 */
@Singleton
public class PrefManager {
    private Context context;

    @Inject
    public PrefManager() {

    }

    /**
     * 初始化pref
     */
    public void initPrefManager(Context context, String dbName) {
        this.context = context;
        PrefUtil.setApplicationPreferences(dbName);
    }

    /**
     * 保存字符数据
     */
    public void save(String key, String value) {
        PrefUtil.savePref(context, key, value);
    }

    /**
     * 获取字符串
     */
    public String getString(String key, String defalutValue) {
        return PrefUtil.getStringPref(context, key, defalutValue);
    }

    /**
     * 保存key
     */
    public void saveKey(String value) {
        save("key", value);
    }

    /**
     * 获取key
     */
    public String getKey() {
        return getString("key", "");
    }

    /**
     * 保存int
     */
    public void save(String key, int value) {
        PrefUtil.savePref(context, key, value);
    }

    /**
     * 获取int
     */
    public int getInt(String key, int defalutValue) {
        return PrefUtil.getIntPref(context, key, defalutValue);
    }
}
