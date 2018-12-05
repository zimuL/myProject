package com.zimu.my2018.appmodel.base;

import com.zimu.my2018.quyoulib.pref_shared_preferences.PrefManager;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/20
 */
public class BaseRepository {

    @Inject
    protected PrefManager prefManager;

    protected boolean mCacheIsDirty = false;

    public void refresh() {
        mCacheIsDirty = true;
    }

    public void clearRepository() {

    }
}
