package com.zimu.my2018.appmodel.repository.login.datasource;

import com.zimu.my2018.quyouapi.data.login.LoginData;
import com.zimu.my2018.quyouapi.data.login.RegisterData;

import io.reactivex.Observable;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/11/7
 */
public class LoginDataSource {

    public interface Local{

    }

    public interface Remote {
        /**
         * login
         */
        Observable<LoginData> login(String user_name, String user_password);

        /**
         * register
         */
        Observable<RegisterData> register(String phone_number, String user_password);
    }
}
