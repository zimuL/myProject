package com.zimu.my2018.quyoulib.net.transform;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/20
 */
public class TransformUtils {
    public static <T> ObservableTransformer<T, T> defaultSchedulers() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
