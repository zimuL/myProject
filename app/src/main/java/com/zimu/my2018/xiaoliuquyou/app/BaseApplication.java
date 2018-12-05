package com.zimu.my2018.xiaoliuquyou.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;

import com.zimu.my2018.quyoulib.core.base.ZimuApplication;
import com.zimu.my2018.quyoulib.net.config.NetConfig;
import com.zimu.my2018.quyoulib.pref_shared_preferences.PrefManager;
import com.zimu.my2018.quyoulib.utils.DateTimeFormat;
import com.zimu.my2018.quyouui.config.ConfigPackage;
import com.zimu.my2018.quyouui.utils.ImageLoadViewUtil;
import com.zimu.my2018.quyouui.widget.loading.LoadingConfig;
import com.zimu.my2018.xiaoliuquyou.R;
import com.zimu.my2018.xiaoliuquyou.core.constants.Constants;
import com.zimu.my2018.xiaoliuquyou.core.constants.URLConstants;
import com.zimu.my2018.xiaoliuquyou.core.di.component.AppComponent;
import com.zimu.my2018.xiaoliuquyou.core.di.component.DaggerAppComponent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import dagger.android.AndroidInjector;

/**
 * 功能：
 * 描述：base
 * Created by hxl on 2018/10/4
 */
public class BaseApplication extends ZimuApplication {

    private static BaseApplication instance;

    @Inject
    PrefManager prefManager;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context,
                                                     @NonNull RefreshLayout layout) {
                //全局设置主题颜色
                layout.setPrimaryColorsId(android.R.color.holo_blue_dark, R.color.colorPrimary);
                return new ClassicsHeader(context).setTimeFormat(new DateTimeFormat("更新于 %s"));
            }
        });
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (null == instance) {
            instance = this;
        }

        initLeakCanary();

        ImageLoadViewUtil.init(this);

        LoadingConfig.init(R.layout.hc_loadding_view, R.layout.hc_preloading_view, R.layout.hc_empty_view, R.layout.hc_error_view);

        //初始化quyouUi包名
        ConfigPackage.init("res://com.zimu.test.myproject/");

        // 初始化网络请求路径
        NetConfig.init(URLConstants.URL_RELEASE_QY, Constants.Debug);

        // 初始化PrefManager
        setPrefManager(Constants.PREFNAME);

    }

    // 初始化PrefManager
    private void setPrefManager(String prefName) {
        prefManager.initPrefManager(this, prefName);
    }

    /**
     * drag2绑定
     */

    @Override
    protected AndroidInjector<? extends ZimuApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

    /**
     * 内存泄露检查
     */
    private boolean initLeakCanary() {
        if (isApkInDebug(this)) {
            //内存泄露检查
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return true;
            }
            LeakCanary.install(this);
        }
        return false;
    }
}
