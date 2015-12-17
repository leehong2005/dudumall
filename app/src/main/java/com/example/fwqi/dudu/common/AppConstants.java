package com.example.fwqi.dudu.common;

/**
 * Created by leehong on 2015/10/11.
 */
public class AppConstants {
    public static final String SERVER_HOST = "http://gateway.dudumall.com";
    //public static final String SERVER_HOST = "http://waibao.xiaogouww.com";
    public static final String RECOMMEND_URL = SERVER_HOST + "/dudu/mall/index";
    public static final String MORE_PRODUCT_URL = SERVER_HOST + "/dudu/product/list";
    public static final String DETAIL_PRODUCT_URL = SERVER_HOST + "/dudu/product/detail";
    public static final String CART_CHECK_URL = SERVER_HOST + "/dudu/order/cart_check";
    public static final String MY_INFO_URL = SERVER_HOST + "/dudu/my/index";
    public static final String SMS_CERTIFY_URL = SERVER_HOST + "/dudu/user/login_sms";
    public static final String LOGIN_URL = SERVER_HOST + "/dudu/user/login_submit";


    public static final int ERROR_CODE_SUCCESS = 0;
    public static final int ERROR_CODE_UNLOGIN = -1000;

    public static final int LOGIN_SUCCESS = 0;
    public static final int LOGIN_FAIL = 1;
    public static final int LOGIN_CANCEL = 2;
    public static final int LOGIN_OUT = 3;

    public static final int MY_UNSPECIFIED_ID = -1;
    public static final int MY_MYSELF_ID = 0;
    public static final int MY_MONEY_ID = 1;
    public static final int MY_RED_ID = 2;
    public static final int MY_ADDRESS_ID = 3;
    public static final int MY_RECHARGE_ID = 4;
    public static final int MY_MESSAGE_ID = 5;
    public static final int MY_EARNING_ID = 6;
    public static final int MY_SETTING_ID = 7;
    public static final int MY_SERVICE_ID = 8;
    public static final int MY_HELP_ID = 9;
    public static final int MY_ABOUT_ID = 10;
    public static final int MY_FEEDBACK_ID = 11;
    public static final int MY_CLEAR_CACHE_ID = 12;
    public static final int MY_VERSION_UPDATE_ID = 13;
    public static final int MY_LOGOUT_ID = 14;

    public static final String SHARED_PREFERENCE_NAME = "DuduPreference";
}
