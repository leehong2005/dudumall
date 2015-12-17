package com.example.fwqi.dudu.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leehong on 2015/10/12.
 */
public class ServiceData {
    private int errNo = 0;
    private String errMsg = null;
    private Map<String, Object> serviceData = new HashMap<>();

    public int getErrNo() {
        return errNo;
    }

    public void setErrNo(int errNo) {
        this.errNo = errNo;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void putData(String key, Object data){
        serviceData.put(key, data);
    }

    public Object getData(String key) {
        return serviceData.get(key);
    }

}
