package com.zimu.my2018.appcase.mian;

import com.zimu.my2018.appcase.mian.fragment_community.CommunityFragment;
import com.zimu.my2018.appcase.mian.fragment_main.MainFragment;
import com.zimu.my2018.appcase.mian.fragment_mine.MineFragment;
import com.zimu.my2018.quyoulib.core.di.scope.PerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
@Module
public abstract class MainModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract MainFragment pMainFragment();

    @PerFragment
    @ContributesAndroidInjector
    abstract MineFragment pMineFragment();

    @PerFragment
    @ContributesAndroidInjector
    abstract CommunityFragment pCommunityFragment();
}
