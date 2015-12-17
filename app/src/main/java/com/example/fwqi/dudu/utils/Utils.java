package com.example.fwqi.dudu.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.fwqi.dudu.MainApplication;

/**
 * Created by leehong on 2015/10/6.
 */
public class Utils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getVersionCode() {
        try {
            PackageManager pm = MainApplication.getInstance().getPackageManager();
            PackageInfo pinfo = pm.getPackageInfo(MainApplication.getInstance().getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return pinfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static String getVersionName() {
        try {
            PackageManager pm = MainApplication.getInstance().getPackageManager();
            PackageInfo pinfo = pm.getPackageInfo(MainApplication.getInstance().getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return pinfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
