package com.zimu.my2018.quyouui.di.module;

import com.zimu.my2018.quyoulib.core.di.scope.PerActivity;
import com.zimu.my2018.quyouui.core.activity.CutPicViewActivity;
import com.zimu.my2018.quyouui.core.activity.PhotoDetailActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/26
 */
@Module
public abstract class ActivityUIBindingModule {
    @PerActivity
    @ContributesAndroidInjector()
    abstract CutPicViewActivity pCutPicViewActivity();

    @PerActivity
    @ContributesAndroidInjector()
    abstract PhotoDetailActivity pPhotoDetailActivity();
}
