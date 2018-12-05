package com.zimu.my2018.appcase.login.loginapp;

import com.zimu.my2018.appmodel.repository.login.LoginRepository;
import com.zimu.my2018.quyouapi.core.netcallback.RequestCallback;
import com.zimu.my2018.quyouapi.data.login.LoginData;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/11/6
 */
public class LoginPresenter implements LoginContract.Presenter {

    @Inject
    LoginRepository mLoginRepository ;

    private LoginContract.View mView ;

    @Inject
    public LoginPresenter() {

    }

    @Override
    public void attachView(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    /**
     * login
     */
    @Override
    public void login(String user_name, String user_password) {
        mLoginRepository.login(user_name, user_password, new RequestCallback<LoginData>() {
            @Override
            public void onSuccess(LoginData response) {
                if (null != mView) {
                    mView.onLoginSuccess(response);
                }
            }

            @Override
            public void onError(String msg) {
                if (null != mView) {
                    mView.onLoginFailed(msg);
                }
            }
        });
    }
}
