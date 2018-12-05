package com.zimu.my2018.quyouapi.core.net;

import com.zimu.my2018.quyouapi.core.api.ApiLoginService;
import com.zimu.my2018.quyouapi.core.api.ApiScenicService;
import com.zimu.my2018.quyoulib.di.module.NetHttpModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/20
 */
@Module
public class NetModule extends NetHttpModule {

    public static boolean isFromService = false;

    /**
     * Scenic api
     */
    @Singleton
    @Provides
    public ApiScenicService providesScenicApi(Retrofit retrofit, OkHttpClient okHttpClient) {
        Retrofit retrofitService = isFromService ? getRetrofit(okHttpClient, "https://h5.hc720.com/") : retrofit;
        return retrofitService.create(ApiScenicService.class);
    }

    /**
     * login api
     */
    @Singleton
    @Provides
    public ApiLoginService providesLoginService(Retrofit retrofit, OkHttpClient okHttpClient ) {
        Retrofit retrofitService = isFromService ? getRetrofit(okHttpClient, "https://h5.hc720.com/") : retrofit;
        return retrofitService.create(ApiLoginService.class);
    }
}
