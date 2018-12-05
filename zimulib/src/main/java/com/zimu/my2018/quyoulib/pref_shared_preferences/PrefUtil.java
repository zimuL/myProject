package com.zimu.my2018.quyoulib.pref_shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.inject.Singleton;

/**
 * 功能：
 * 描述：SharedPreferences调用
 * Created by hxl on 2018/10/20
 */
@Singleton
public class PrefUtil {
    private static String APPLICATION_PREFERENCES = "qy_prefs";
    private static SharedPreferences mPref;
    private static SharedPreferences.Editor mEditor;

    public static void setApplicationPreferences(String applicationPreferences) {
        APPLICATION_PREFERENCES = applicationPreferences;
    }

    private static void initPref(Context context) {
        if (null == mPref) {
            mPref = context.getSharedPreferences(APPLICATION_PREFERENCES, Context.MODE_PRIVATE);
        }
    }

    public static void removePref(Context context, String prefKey) {
        initPref(context);
        mEditor = mPref.edit();
        mEditor.remove(prefKey);
        mEditor.apply();
    }

    /**
     * 保存字符串Pref
     */
    public static void savePref(Context context, String key, String value) {
        initPref(context);
        mEditor = mPref.edit();
        mEditor.putString(key, value);
        mEditor.apply();
    }

    /**
     * 保存布尔值Pref
     */
    public static void savePref(Context context, String key, boolean value) {
        initPref(context);
        mEditor = mPref.edit();
        mEditor.putBoolean(key, value);
        mEditor.apply();
    }

    /**
     * 保存整形Pref
     */
    public static void savePref(Context context, String key, int value) {
        initPref(context);
        mEditor = mPref.edit();
        mEditor.putInt(key, value);
        mEditor.apply();
    }

    /**
     * 获取pref中key对应的字符串值
     */
    public static String getStringPref(Context context, String key, String defaultValue) {
        initPref(context);
        return mPref.getString(key, defaultValue);
    }

    /**
     * 获取pref中key对应的布尔值
     */
    public static boolean getBooleanPref(Context context, String key, boolean defaultValue) {
        initPref(context);
        return mPref.getBoolean(key, defaultValue);
    }

    /**
     * 获取pref中key对应的整形
     */
    public static int getIntPref(Context context, String key, int defaultValue) {
        initPref(context);
        return mPref.getInt(key, defaultValue);
    }

    /**
     * 获取pref中key对应的整形
     */
    public static Object getObjectPref(Context context, String key) {
        Object object = null;
        String objectBase64 = PrefUtil.getStringPref(context, key, "");
        if ("".equals(objectBase64)) {
            return null;
        }
        //读取字节
        byte[] csBase64 = Base64.decode(objectBase64.getBytes(), Base64.DEFAULT);
        //封装到字节流
        ByteArrayInputStream bais = new ByteArrayInputStream(csBase64);
        try {
            //再次封装
            ObjectInputStream bis = new ObjectInputStream(bais);
            try {
                //读取对象
                object = bis.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }
}
