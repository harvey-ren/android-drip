package com.rqphp.publib.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Havey on 2017/10/24.
 * 界面跳转的工具类
 */

public class PageJumpUtil {

    /**
     * 跳转到对应页面
     *
     * @param context
     * @param cls
     */
    public static void forwordToPage(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }


    /**
     * 跳转到拨号界面，同时传递电话号码
     *
     * @param context
     * @param phone
     */
    public static void forwordToPhonePage(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        context.startActivity(intent);
    }


    /**
     * @param context
     * @param cls
     * @param key
     * @param value
     */
    public static void forwordToPageWithIntValue(Context context, Class<?> cls, String key, int value) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(key, value);
        context.startActivity(intent);
    }


    /**
     * @param context
     * @param cls
     * @param key
     * @param value
     */
    public static void forwordToPageWithStrinValue(Context context, Class<?> cls, String key, String value) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(key, value);
        context.startActivity(intent);
    }


    /**
     * @param context
     * @param cls
     * @param key
     * @param intList
     */
    public static void forwordToPageWithIntList(Context context, Class<?> cls, String key, ArrayList<Integer> intList) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(key, intList);
        context.startActivity(intent);
    }


    /**
     * @param context
     * @param cls
     * @param key
     * @param object
     */
    public static void forwordToPageWithObject(Context context, Class<?> cls, String key, Object object) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(key, (Serializable) object);
        context.startActivity(intent);
    }


    /**
     * 跳转到wifi设置界面
     *
     * @param context
     */
    public static void forwordToWifiSetting(Context context) {
        Intent intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
        context.startActivity(intent);
    }

    /**
     * 跳转到系统设置界面
     *
     * @param context
     */
    public static void forwordToSystemSetting(Context context) {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        context.startActivity(intent);
    }


    /**
     * 跳转到浏览器界面
     *
     * @param context
     * @param urlStr
     */
    public static void forwordToWebBrowser(Context context, String urlStr) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri content_url = Uri.parse(urlStr);
        intent.setData(content_url);
        context.startActivity(intent);
    }

    /**
     * IP设置界面
     *
     * @param context
     */
    public static void forwordToIpSetting(Context context) {
        Intent intent = new Intent(Settings.ACTION_WIFI_IP_SETTINGS);
        context.startActivity(intent);
    }
}
