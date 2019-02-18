package com.rqphp.publib.util;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 字符串相关的工具类
 * Created by Harvey on 2017/10/16.
 */

public class StringUtils {


    /**
     * Get String of utf-8
     *
     * @return XML-Formed string
     */
    public static String getUTF8String(String string) {
        String strUTF8 = "";
        try {
            strUTF8 = URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return strUTF8;
    }

    /**
     * 字符串字节长度
     *
     * @param string
     * @return
     */
    public static int getStrByteLength(String string) {
        int length = 0;
        if (!TextUtils.isEmpty(string)) {
            try {
                length = string.getBytes("utf-8").length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return length;
    }

    /**
     * 字节转成字符串
     *
     * @param data
     * @return
     */
    public static String byteToString(byte[] data) {
        String newData = "";
        try {
            newData = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            return newData;
        }
    }


    /**
     * 获取到最后字符串
     *
     * @param string
     * @return
     */
    public static String getLastString(String string) {
        return getLastString(string, "");
    }

    /**
     * 获取到最后字符串
     *
     * @param string
     * @param defaultStr 默认字符串
     * @return
     */
    public static String getLastString(String string, String defaultStr) {
        return TextUtils.isEmpty(string) ? defaultStr : string;
    }
}
