package com.zimu.my2018.di.module;

import com.zimu.my2018.appcase.login.loginapp.LoginActivity;
import com.zimu.my2018.appcase.login.registerapp.RegisterActivity;
import com.zimu.my2018.appcase.mian.MainActivity;
import com.zimu.my2018.appcase.mian.MainModule;
import com.zimu.my2018.appcase.scenic.scenicattentionlist.ScenicAttentionListActivity;
import com.zimu.my2018.appcase.scenic.scenicbannerpicture.ScenicBannerPictureActivity;
import com.zimu.my2018.appcase.scenic.scenicdetail.ScenicDetailActivity;
import com.zimu.my2018.appcase.scenic.scenicevaluate.ScenicEvaluateActivity;
import com.zimu.my2018.appcase.scenic.scenicevaluatelist.ScenicEvaluateListActivity;
import com.zimu.my2018.appcase.scenic.scenicfootprint.ScenicFootprintActivity;
import com.zimu.my2018.appcase.scenic.scenicfreehandmap.ScenicFreehandMapActivity;
import com.zimu.my2018.appcase.scenic.scenicnearby.ScenicNearbyActivity;
import com.zimu.my2018.appcase.search.searchscenic.SearchScenicActivity;
import com.zimu.my2018.appcase.user.functionintroduce.FunctionIntroduceActivity;
import com.zimu.my2018.appcase.user.settings.SettingsActivity;
import com.zimu.my2018.quyoulib.core.di.scope.PerActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
@Module
public abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity pMainActivity();

    @PerActivity
    @ContributesAndroidInjector()
    abstract ScenicDetailActivity pScenicDetailActivity();

    @PerActivity
    @ContributesAndroidInjector()
    abstract ScenicFreehandMapActivity pScenicFreehandMapActivity();

    @PerActivity
    @ContributesAndroidInjector()
    abstract SearchScenicActivity pSearchScenicActivity();

    @PerActivity
    @ContributesAndroidInjector()
    abstract ScenicNearbyActivity pScenicNearbyActivity();

    @PerActivity
    @ContributesAndroidInjector()
    abstract ScenicEvaluateActivity pScenicEvaluateActivity();

    @PerActivity
    @ContributesAndroidInjector()
    abstract ScenicAttentionListActivity pScenicAttentionListActivity();

    @PerActivity
    @ContributesAndroidInjector()
    abstract ScenicEvaluateListActivity pScenicEvaluateListActivity();

    @PerActivity
    @ContributesAndroidInjector()
    abstract ScenicFootprintActivity pScenicFootprintActivity();

    @PerActivity
    @ContributesAndroidInjector()
    abstract ScenicBannerPictureActivity pScenicBannerPictureActivity();

    @PerActivity
    @ContributesAndroidInjector()
    abstract FunctionIntroduceActivity pFunctionIntroduceActivity();

    @PerActivity
    @ContributesAndroidInjector()
    abstract SettingsActivity pSettingsActivity();

    @PerActivity
    @ContributesAndroidInjector()
    abstract LoginActivity pLoginActivity();

    @PerActivity
    @ContributesAndroidInjector()
    abstract RegisterActivity pRegisterActivity();

}
