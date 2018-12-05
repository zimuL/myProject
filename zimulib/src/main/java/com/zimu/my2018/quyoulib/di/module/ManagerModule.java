package com.zimu.my2018.quyoulib.di.module;

import android.content.Context;

import com.zimu.my2018.quyoulib.location.AddrSearchManager;
import com.zimu.my2018.quyoulib.location.LocationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/13
 */
@Module
public class ManagerModule {

    @Singleton
    @Provides
    public LocationManager providesLocationManager(Context context) {
        return new LocationManager(context);
    }

    @Singleton
    @Provides
    public AddrSearchManager providesAddrSearchManager(Context context) {
        return new AddrSearchManager(context);
    }
}
