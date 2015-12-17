package com.example.fwqi.dudu.Manager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.fwqi.dudu.MainApplication;
import com.example.fwqi.dudu.activity.LoginActivity;
import com.example.fwqi.dudu.activity.PhoneLoginActivity;
import com.example.fwqi.dudu.common.AppConstants;
import com.example.fwqi.dudu.data.LoginData;
import com.example.fwqi.dudu.event.LoginEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import de.greenrobot.event.EventBus;

/**
 * Created by leehong on 2015/11/6.
 */
public class LoginManager {
    private static LoginManager instance = null;
    private LoginData loginData = null;

    public interface OnLoginChangedListener {
        void onLoginChanged(int loginStatus);
    }
    private List<OnLoginChangedListener> loginChangedListenerList = new ArrayList<>();
    public void addLoginChangedListener(OnLoginChangedListener listener) {
        loginChangedListenerList.add(listener);
    }
    public void removeLoginChangedListener(OnLoginChangedListener listener) {
        loginChangedListenerList.remove(listener);
    }

    private LoginManager() {
        loginData = getLoginDataFromLocal();
    }

    public synchronized static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
            EventBus.getDefault().register(instance);
        }

        return instance;
    }

    public void login(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public void logout() {
        clearLoginDataFromLocal();
        loginData = null;
        if (loginChangedListenerList != null) {
            for (OnLoginChangedListener listener : loginChangedListenerList) {
                if (listener != null) {
                    listener.onLoginChanged(AppConstants.LOGIN_OUT);
                }
            }
        }
    }

    public void goToPhoneLogin(Context context) {
        Intent intent = new Intent(context, PhoneLoginActivity.class);
        context.startActivity(intent);
    }

    public boolean isLogin() {
        return loginData != null;
    }

    public void onEvent(LoginEvent event) {
        if (event.getLoginStatus() == AppConstants.LOGIN_SUCCESS){
            // Save login status to share preference.
            loginData = event.getLoginData();
            saveLoginDataToLocal(loginData);
        }
        // Execute the login change listener's callback function.
        if (loginChangedListenerList != null) {
            for (OnLoginChangedListener changedListener : loginChangedListenerList) {
                if (changedListener != null) {
                    changedListener.onLoginChanged(event.getLoginStatus());
                }
            }
        }
    }

    public LoginData getLoginData() {
        return loginData;
    }

    /**
     * Save the login data to SharedPreferences.
     * @param loginData
     */
    private void saveLoginDataToLocal(LoginData loginData) {
        if (loginData != null) {
            SharedPreferences sharedPreferences = MainApplication.getInstance().
                    getSharedPreferences(AppConstants.SHARED_PREFERENCE_NAME,
                            Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userName", loginData.getUserName());
            editor.putString("uid", loginData.getUid());
            editor.putString("token", loginData.getToken());
            editor.putString("phoneNumber", loginData.getPhoneNumber());
            editor.commit();
        }
    }

    /**
     * Get the login data from SharedPreferences.
     */
    private LoginData getLoginDataFromLocal(){
        LoginData loginData = null;
        SharedPreferences sharedPreferences = MainApplication.getInstance().
                getSharedPreferences(AppConstants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        if (token != null) {
            loginData = new LoginData();
            loginData.setUid(sharedPreferences.getString("uid", ""));
            loginData.setUserName(sharedPreferences.getString("userName", ""));
            loginData.setToken(sharedPreferences.getString("token", ""));
            loginData.setPhoneNumber(sharedPreferences.getString("phoneNumber", ""));
        }

        return loginData;
    }

    private void clearLoginDataFromLocal() {
        SharedPreferences sharedPreferences = MainApplication.getInstance().
                getSharedPreferences(AppConstants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public synchronized static void releaseInstance() {
        EventBus.getDefault().unregister(instance);
        instance = null;
    }

}
