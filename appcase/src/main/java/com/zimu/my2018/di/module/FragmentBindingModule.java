package com.zimu.my2018.di.module;

import com.zimu.my2018.appcase.mian.fragment_main.fragment.fragment_footprint.FootPrintChooseFragment;
import com.zimu.my2018.appcase.mian.fragment_main.fragment.fragment_scenic.ScenicListFragment;
import com.zimu.my2018.appcase.mian.fragment_main.fragment.fragment_traveller.TravellerFragment;
import com.zimu.my2018.quyoulib.core.di.scope.PerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
@Module
public abstract class FragmentBindingModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract ScenicListFragment pScenicListFragment();

    @PerFragment
    @ContributesAndroidInjector
    abstract FootPrintChooseFragment pFootPrintChooseFragment();

    @PerFragment
    @ContributesAndroidInjector
    abstract TravellerFragment pTravellerFragment();

}
