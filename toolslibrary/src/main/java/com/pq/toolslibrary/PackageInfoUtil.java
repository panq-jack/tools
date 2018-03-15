package com.pq.toolslibrary;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.text.TextUtils;

/**
 *  get versionCode  versionName
 */
public class PackageInfoUtil {

    private static String versionName;
    private static int versionCode;

    public static String getVersionName(Application application) {
        if(TextUtils.isEmpty(versionName)) {
            PackageInfo packageInfo = getPackageInfo(application);
            versionName = null == packageInfo ? "" : packageInfo.versionName;
        }
        return versionName;
    }
    public static int getVersionCode(Application application) {
        if(0 == versionCode) {
            PackageInfo packageInfo = getPackageInfo(application);
            versionCode = null == packageInfo ? 0 : packageInfo.versionCode;
        }
        return versionCode;
    }

    private static PackageInfo getPackageInfo(Application application) {
        try {
            PackageInfo packageInfo = application.getPackageManager()
                    .getPackageInfo(application.getPackageName(), 0);
            return packageInfo;
        } catch (Exception var2) {
            return null;
        }
    }

    public static String demo(Application application){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("versionCode:  ").append(getVersionCode(application)).append("\n")
                .append("versionName:  ").append(getVersionName(application));

        return TextUtils.isEmpty(stringBuilder.toString())?"暂无version数据":stringBuilder.toString();
    }
}
