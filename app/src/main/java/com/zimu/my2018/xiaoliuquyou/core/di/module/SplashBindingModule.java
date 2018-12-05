package com.zimu.my2018.xiaoliuquyou.core.di.module;

import com.zimu.my2018.quyoulib.core.di.scope.PerActivity;
import com.zimu.my2018.xiaoliuquyou.app.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
@Module
public abstract class SplashBindingModule {
    @PerActivity
    @ContributesAndroidInjector
    abstract SplashActivity pSplashActivity();
}
