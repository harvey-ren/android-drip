package com.rqphp.publib.util;

import com.orhanobut.logger.Logger;


/**
 * Created by Harvey on 2017/7/25.
 * log  util
 */

public class LogUtil {

    /**
     * error log
     *
     * @param msg
     */
    public static void error(String msg) {
        Logger.e(msg == null ? "" : msg);
    }


    /**
     * info log
     *
     * @param msg
     */
    public static void info(String msg) {
        Logger.i(msg == null ? "" : msg);
    }


    /**
     * debug log
     *
     * @param msg
     */
    public static void debug(String msg) {
        Logger.d(msg == null ? "" : msg);
    }


    /**
     * warn log
     *
     * @param msg
     */
    public static void warn(String msg) {
        Logger.w(msg == null ? "" : msg);
    }


    /**
     * json log
     *
     * @param msg
     */
    public static void json(String msg) {
        Logger.json(msg == null ? "" : msg);
    }


    /**
     * xml log
     *
     * @param msg
     */
    public static void xml(String msg) {
        Logger.xml(msg == null ? "" : msg);
    }

}
