package com.zimu.my2018.appmodel.repository.login.datasource.local;

import com.zimu.my2018.appmodel.base.BaseLocalDataRepositorySource;
import com.zimu.my2018.appmodel.repository.login.datasource.LoginDataSource;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/11/7
 */
public class LoginLocalDataSource extends BaseLocalDataRepositorySource implements LoginDataSource.Local{

    @Inject
    public LoginLocalDataSource() {

    }
}
