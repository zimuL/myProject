package com.zimu.my2018.quyoulib.core.base;

import android.content.Context;
import android.support.multidex.MultiDex;

import dagger.android.DaggerActivity;
import dagger.android.DaggerApplication;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
public abstract class HcApplication extends DaggerApplication{

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }
}
