package com.zimu.my2018.quyouapi.data.login;

import java.io.Serializable;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/11/7
 */
public class UserBean implements Serializable {

    private String user_password;
    private String open_id;
    private String user_origin_id;
    private int user_age;
    private String user_city;
    private String user_gender;
    private long register_time;
    private String user_icon_url;
    private int user_id;
    private String nickname;
    private long phone_number;
    private String user_interest;
    private String user_description;

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getUser_origin_id() {
        return user_origin_id;
    }

    public void setUser_origin_id(String user_origin_id) {
        this.user_origin_id = user_origin_id;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public long getRegister_time() {
        return register_time;
    }

    public void setRegister_time(long register_time) {
        this.register_time = register_time;
    }

    public String getUser_icon_url() {
        return user_icon_url;
    }

    public void setUser_icon_url(String user_icon_url) {
        this.user_icon_url = user_icon_url;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public String getUser_interest() {
        return user_interest;
    }

    public void setUser_interest(String user_interest) {
        this.user_interest = user_interest;
    }

    public String getUser_description() {
        return user_description;
    }

    public void setUser_description(String user_description) {
        this.user_description = user_description;
    }
}
