package com.zimu.my2018.quyouapi.core.netcallback;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/20
 */
public interface RequestCallback<R> {
    void onSuccess(R response);

    void onError(String msg);
}

