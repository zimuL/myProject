package com.zimu.my2018.appmodel.repository.login.datasource.remote;

import com.zimu.my2018.appmodel.repository.login.datasource.LoginDataSource;
import com.zimu.my2018.quyouapi.core.api.ApiLoginService;
import com.zimu.my2018.quyouapi.data.login.LoginData;
import com.zimu.my2018.quyouapi.data.login.RegisterData;
import com.zimu.my2018.quyoulib.net.transform.TransformUtils;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/11/7
 */
public class LoginRemoteDataSource implements LoginDataSource.Remote {

    @Inject
    ApiLoginService mApiLoginService ;

    @Inject
    public LoginRemoteDataSource() {

    }

    /**
     * login
     */
    @Override
    public Observable<LoginData> login(String user_name, String user_password) {
        ObservableTransformer<LoginData, LoginData> transformer = TransformUtils.defaultSchedulers();
        Observable<LoginData> compose = mApiLoginService.login(user_name, user_password)
                .compose(transformer);
        return compose;
    }

    /**
     * register
     */
    @Override
    public Observable<RegisterData> register(String phone_number, String user_password) {
        ObservableTransformer<RegisterData, RegisterData> transformer = TransformUtils.defaultSchedulers();
        Observable<RegisterData> compose = mApiLoginService.register(phone_number, user_password)
                .compose(transformer);
        return compose;
    }
}
