package com.example.fwqi.dudu.event;

import com.example.fwqi.dudu.data.LoginData;

/**
 * Created by leehong on 2015/11/7.
 */
public class LoginEvent {
    private int loginStatus = -1;
    private LoginData loginData = null;

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }
}
