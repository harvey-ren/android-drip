package com.rqphp.publib.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * 资源相关的工具类
 * Created by Harvey on 2017/11/24.
 */

public class ResourcesUtil {


    /**
     * 从资源文件中获取字符串
     *
     * @param context
     * @param id
     * @return
     */
    public static String getString(Context context, int id) {
        return context.getResources().getString(id);
    }


    /**
     * 从资源文件中获取颜色
     *
     * @param context
     * @param id
     * @return
     */
    public static int getColor(Context context, int id) {
        return context.getResources().getColor(id);
    }


    /**
     * 从资源文件中获取字符串数组
     *
     * @param context
     * @param id
     * @return
     */
    public static String[] getStringArray(Context context, int id) {
        return context.getResources().getStringArray(id);
    }


    /**
     * 获取到图片
     *
     * @param context
     * @param id
     * @return
     */
    public static Drawable getDrawable(Context context, int id) {
        return context.getResources().getDrawable(id);
    }

}
