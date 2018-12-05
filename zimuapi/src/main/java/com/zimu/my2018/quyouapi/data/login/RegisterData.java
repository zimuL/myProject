package com.zimu.my2018.quyouapi.data.login;

import java.io.Serializable;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/11/7
 */
public class RegisterData implements Serializable {

    private int type;
    private UserBean user;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
