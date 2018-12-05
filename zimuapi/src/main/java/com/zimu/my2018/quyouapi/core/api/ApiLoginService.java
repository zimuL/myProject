package com.zimu.my2018.quyouapi.core.api;

import com.zimu.my2018.quyouapi.data.login.LoginData;
import com.zimu.my2018.quyouapi.data.login.RegisterData;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/11/7
 */
public interface ApiLoginService {

    /**
     * login
     */
    @POST("user/userLogin.do")
    @FormUrlEncoded
    Observable<LoginData> login(
            @Field("user_name") String user_name,
            @Field("user_password") String user_password
    );

    /**
     * register
     */
    @POST("user/userRegister.do")
    @FormUrlEncoded
    Observable<RegisterData> register(
            @Field("phone_number") String phone_number,
            @Field("user_password") String user_password
    );
}
