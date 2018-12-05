package com.zimu.my2018.appmodel.repository.login;

import android.util.Log;

import com.zimu.my2018.appmodel.base.BaseRepository;
import com.zimu.my2018.appmodel.repository.login.datasource.local.LoginLocalDataSource;
import com.zimu.my2018.appmodel.repository.login.datasource.remote.LoginRemoteDataSource;
import com.zimu.my2018.quyouapi.core.netcallback.RequestCallback;
import com.zimu.my2018.quyouapi.data.login.LoginData;
import com.zimu.my2018.quyouapi.data.login.RegisterData;
import com.zimu.my2018.quyouapi.data.login.UserBean;
import com.zimu.my2018.quyoulib.pref_shared_preferences.PrefManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/11/7
 */
@Singleton
public class LoginRepository extends BaseRepository {

    @Inject
    LoginLocalDataSource mLoginLocalDataSource ;

    @Inject
    LoginRemoteDataSource mLoginRemoteDataSource ;

    @Inject
    public LoginRepository() {

    }

    /**
     * login
     */
    public void login(String user_name, String user_password, final RequestCallback<LoginData> requestCallback) {
        mLoginRemoteDataSource.login(user_name, user_password)
                .subscribe(new Observer<LoginData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginData loginData) {
                        UserBean user = loginData.getUser();
                        int userId = user.getUser_id();
                        String userUrl = user.getUser_icon_url();
                        String nickname = user.getNickname();
                        prefManager.save("userId", userId);
                        prefManager.save("userUrl", userUrl);
                        prefManager.save("nickname", nickname);
                        requestCallback.onSuccess(loginData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * register
     */
    public void register(String phone_number, String user_password, final RequestCallback<RegisterData> requestCallback) {
        mLoginRemoteDataSource.register(phone_number, user_password)
                .subscribe(new Observer<RegisterData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(RegisterData registerData) {
                        requestCallback.onSuccess(registerData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
