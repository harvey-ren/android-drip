package com.rqphp.publib.util;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 软键盘相关的工具类
 * Created by Harvey on 2017/11/17.
 */

public class InputMethodUtil {


    /**
     * 显示软键盘
     *
     * @param context
     * @param editText
     */
    public static void showSoftKeyboard(Context context, EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        }
    }


    /**
     * 根据传入控件的坐标和用户的焦点坐标，判断是否隐藏键盘，如果点击的位置在控件内，则不隐藏键盘
     *
     * @param view  控件view
     * @param event 焦点位置
     * @return 是否隐藏
     */
    public static void hideSoftKeyboard(Context context, View view, MotionEvent event) {
        try {
            if (view != null && view instanceof EditText) {
                int[] location = {0, 0};
                view.getLocationInWindow(location);
                int left = location[0], top = location[1], right = left + view.getWidth(), bootom = top + view.getHeight();
                // 判断焦点位置坐标是否在空间内，如果位置在控件外，则隐藏键盘
                if (event.getRawX() < left || event.getRawX() > right || event.getY() < top || event.getRawY() > bootom) {
                    hideSoftKeyboard(context, view);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param context
     * @param view
     */
    public static void hideSoftKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS); //强制隐藏键盘
    }

}
