package com.rqphp.publib.util;

import android.text.TextUtils;

/**
 * Created by Harvey on 2017/9/7.
 */

public class UrlCheckUtil {

    private final static String HTTP_PREFIX = "http://";

    private final static String HTTPS_PREFIX = "https://";

    public static boolean isCompleteUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        return url.startsWith(HTTP_PREFIX) || url.startsWith(HTTPS_PREFIX);
    }
}
