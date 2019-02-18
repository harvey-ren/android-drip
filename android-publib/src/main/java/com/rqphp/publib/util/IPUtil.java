package com.rqphp.publib.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ip地址相关的工具类
 * Created by Harvey on 2017/11/23.
 */

public class IPUtil {


    /**
     * 是否是ip地址
     *
     * @param address
     * @return
     */
    public static boolean isIPAddress(String address) {
        if (TextUtils.isEmpty(address)) {
            return false;
        }
        //判断IP格式和范围
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(address);
        boolean middleAddress = mat.find();

        if (middleAddress) {
            String ips[] = address.split("\\.");
            if (ips.length == 4) {
                for (String ip : ips) {
                    if (Integer.parseInt(ip) < 0 || Integer.parseInt(ip) > 255) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
