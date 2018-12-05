package com.zimu.my2018.quyoulib.core.di.mvp;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
public interface BasePresenter<V> {
    void attachView(V view);

    void detachView();
}
