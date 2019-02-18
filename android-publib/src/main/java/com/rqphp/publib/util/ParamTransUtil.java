package com.rqphp.publib.util;

import java.util.HashMap;
import java.util.Map;

public class ParamTransUtil {

    private static final Map<String, Object> mParams = new HashMap<>();

    /**
     * 存放参数
     *
     * @param key
     * @param param
     */
    public static void putParam(String key, Object param) {
        mParams.put(key, param);
    }


    /**
     * @param key
     * @return
     */
    public static Object getParam(String key) {
        return mParams.get(key);
    }


    /**
     * 删除某个参数，一般在返回的页面接收到值以后，在onDestory 方法里面调用
     *
     * @param key
     */
    public static void removeParam(String key) {
        mParams.remove(key);
    }


    /**
     * 清除所有参数，在退出之后,以及初步进入程序的时候
     */
    public static void clearParams() {
        mParams.clear();
    }


    /**
     * 取参数同时移除参数
     *
     * @param key
     * @return
     */
    public static Object getParamWithRemove(String key) {
        Object res = mParams.get(key);
        mParams.remove(key);
        return res;
    }
}
