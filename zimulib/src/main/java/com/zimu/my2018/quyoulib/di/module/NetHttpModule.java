package com.zimu.my2018.quyoulib.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zimu.my2018.quyoulib.net.adapter.DoubleDefault0Adapter;
import com.zimu.my2018.quyoulib.net.adapter.IntegerDefault0Adapter;
import com.zimu.my2018.quyoulib.net.adapter.LongDefault0Adapter;
import com.zimu.my2018.quyoulib.net.adapter.NullStringToEmptyAdapterFactory;
import com.zimu.my2018.quyoulib.net.config.NetConfig;
import com.zimu.my2018.quyoulib.net.interceptor.AppInterceptor;
import com.zimu.my2018.quyoulib.utils.AppUtils;
import com.zimu.my2018.quyoulib.utils.SSLSocketUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.SSLSocketFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/20
 */
@Module
public class NetHttpModule {
    public static final long DEFAULT_TIME_OUT = 15000;

    @Singleton
    @Provides
    public OkHttpClient provideDefaultHttpClient(Context context) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(NetConfig.getDEBUG() ? HttpLoggingInterceptor.Level.BASIC : HttpLoggingInterceptor.Level.NONE);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        builder.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//读操作超时时间
        int version = AppUtils.getVersionCode(context);
        builder.addInterceptor(new AppInterceptor(version));
        builder.addInterceptor(loggingInterceptor);
        SSLSocketFactory socketFactory = SSLSocketUtils.getSocketFactory(context);
        if (socketFactory != null) {
            builder.sslSocketFactory(socketFactory);

        }
        builder.hostnameVerifier((hostname, session) -> true);

        return builder.build();
    }


    @Singleton
    @Provides
    public Retrofit providesDefaultRetrofit(OkHttpClient okHttpClient) {

        //创建gson对象
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())
                .registerTypeAdapter(long.class, new LongDefault0Adapter())
                .serializeNulls()
                .create();

        //创建retrofit对象
        return new Retrofit.Builder()
                .baseUrl(NetConfig.getBaseUrl())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addConverterFactory(ResponseConverterFactory.create())
                .build();
    }


    public Retrofit getRetrofit(OkHttpClient okHttpClient, String baseUrl) {

        //创建gson对象
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())
                .registerTypeAdapter(long.class, new LongDefault0Adapter())
                .serializeNulls()
                .create();

        //创建retrofit对象
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addConverterFactory(ResponseConverterFactory.create())
                .build();
    }
}
