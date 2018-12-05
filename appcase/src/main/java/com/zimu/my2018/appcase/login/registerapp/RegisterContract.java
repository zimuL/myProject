package com.zimu.my2018.appcase.login.registerapp;

import com.zimu.my2018.quyouapi.data.login.RegisterData;
import com.zimu.my2018.quyoulib.core.di.mvp.BasePresenter;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/11/7
 */
public class RegisterContract {

    interface View {
        void onRegisterSuccess(RegisterData response);

        void onRegisterFailed(String msg);
    }

    interface Presenter extends BasePresenter<View>{

        void register(String phone_number, String user_password);
    }
}
