package com.example.fwqi.dudu.common;

import android.util.Log;

import com.example.fwqi.dudu.Manager.LoginManager;
import com.example.fwqi.dudu.data.LoginData;
import com.example.fwqi.dudu.utils.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by leehong on 2015/11/18.
 */
public class Parameter {
    private Map<String, String> parameterMap = null;

    public Parameter() {
        parameterMap = new HashMap<>();
    }

    public void add(String key, String value) {
        parameterMap.put(key, value);
    }

    public String getParamString(boolean hasCommonParam) {
        StringBuilder stringBuilder = new StringBuilder();
        if (hasCommonParam){
            stringBuilder.append(getCommonParamString());
        }

        if (parameterMap != null) {
            Iterator iterator = parameterMap.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry entry = (Map.Entry) iterator.next();
                stringBuilder.append("&"+ entry.getKey() + "=" + entry.getValue());
            }
        }

        String params = stringBuilder.toString();
        Log.d("dudu", "getParamString params = " + params);
        return params;
    }

    private String getCommonParamString() {
        String uid = null;
        String token = null;
        LoginData loginData = LoginManager.getInstance().getLoginData();
        if (loginData != null) {
            uid = loginData.getUid();
            token = loginData.getToken();
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("client=android")
                .append("&client_os=" + android.os.Build.VERSION.RELEASE)
                .append("&version_name=" + Utils.getVersionName())
                .append("&version_code=" + Utils.getVersionCode())
                .append("&uid=" + uid)
                .append("&utoken=" + token)
                .append("&lat=" + "")
                .append("&lon=" + "")
                .append("&place=" + "");

        return  stringBuilder.toString();
    }

}
