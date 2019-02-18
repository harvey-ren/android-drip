package com.rqphp.publib.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.Locale;


/**
 * Created by Harvey on 2017/7/25.
 */

public class SystemUtil {


    /**
     * get version code
     *
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = 1;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionCode;
    }


    /**
     * get version name
     *
     * @return
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }


    /**
     * get sdk version
     *
     * @return
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }


    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }


    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }


}
