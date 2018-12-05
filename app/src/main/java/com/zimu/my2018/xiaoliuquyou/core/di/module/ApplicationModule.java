package com.zimu.my2018.xiaoliuquyou.core.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
@Module
public abstract class ApplicationModule {
    @Binds
    abstract Context bindContext(Application application);
}
