package com.rqphp.publib.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Harvey on 2017/7/24.
 */
public class ScreenUtil {

    private static int screenWidth = 0;

    private static int screenHeight = 0;

    private static float screenDensity = 0f;

    /**
     * init screen info
     */
    public static void initScreenInfo(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        screenDensity = displayMetrics.density;
    }

    /**
     * get screen width
     *
     * @return
     */
    public static int getScreenWidth() {
        return screenWidth;
    }


    /**
     * get screen height
     *
     * @return
     */
    public static int getScreenHeight() {
        return screenHeight;
    }


    /**
     * get screen density
     *
     * @return
     */
    public static float getScreenDensity() {
        return screenDensity;
    }

    /**
     * swtich  dp to px
     *
     * @param dpValue
     */
    public static int dp2px(float dpValue) {
        return (int) (dpValue * getScreenDensity() + 0.5f);
    }


    /**
     * swtich px to dp
     *
     * @param pxValue
     * @return
     */
    public static int px2dp(float pxValue) {
        return (int) (pxValue / getScreenDensity() + 0.5f);
    }


}
