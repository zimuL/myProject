package com.zimu.my2018.appcase.login.registerapp;

import com.zimu.my2018.appmodel.repository.login.LoginRepository;
import com.zimu.my2018.quyouapi.core.netcallback.RequestCallback;
import com.zimu.my2018.quyouapi.data.login.RegisterData;
import com.zimu.my2018.quyoulib.core.di.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/11/7
 */
public class RegisterPresenter implements RegisterContract.Presenter {

    @Inject
    LoginRepository mLoginRepository ;

    private RegisterContract.View mView;

    @Inject
    public RegisterPresenter() {

    }

    @Override
    public void attachView(RegisterContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    /**
     * register
     */
    @Override
    public void register(String phone_number, String user_password) {
        mLoginRepository.register(phone_number, user_password, new RequestCallback<RegisterData>() {
            @Override
            public void onSuccess(RegisterData response) {
                if (null != mView) {
                    mView.onRegisterSuccess(response);
                }
            }

            @Override
            public void onError(String msg) {
                if (null != mView) {
                    mView.onRegisterFailed(msg);
                }
            }
        });
    }
}
