package com.zimu.my2018.appcase.login.loginapp;

import com.zimu.my2018.quyouapi.data.login.LoginData;
import com.zimu.my2018.quyoulib.core.di.mvp.BasePresenter;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/11/6
 */
public class LoginContract {

    interface View {
        void onLoginSuccess(LoginData loginData);

        void onLoginFailed(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void login(String user_name, String user_password);
    }
}
