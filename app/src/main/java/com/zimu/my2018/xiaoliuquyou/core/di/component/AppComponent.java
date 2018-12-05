package com.zimu.my2018.xiaoliuquyou.core.di.component;

import android.app.Application;

import com.zimu.my2018.appcase.mian.MainModule;
import com.zimu.my2018.di.module.ActivityBindingModule;
import com.zimu.my2018.di.module.FragmentBindingModule;
import com.zimu.my2018.quyouapi.core.net.NetModule;
import com.zimu.my2018.quyoulib.di.module.ManagerModule;
import com.zimu.my2018.quyouui.di.module.ActivityUIBindingModule;
import com.zimu.my2018.xiaoliuquyou.app.BaseApplication;
import com.zimu.my2018.xiaoliuquyou.core.di.module.ApplicationModule;
import com.zimu.my2018.xiaoliuquyou.core.di.module.SplashBindingModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
@Singleton
@Component(modules = {ApplicationModule.class, SplashBindingModule.class,
        ActivityBindingModule.class, FragmentBindingModule.class, MainModule.class,
        ActivityUIBindingModule.class, AndroidSupportInjectionModule.class,
        ManagerModule.class, NetModule.class})
public interface AppComponent extends AndroidInjector<BaseApplication>{
    @Override
    void inject(BaseApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
