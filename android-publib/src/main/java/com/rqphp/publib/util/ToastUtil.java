package com.rqphp.publib.util;

import android.content.Context;
import android.widget.Toast;

import com.rqphp.publib.widget.CustomToast;


/**
 * Created by Harvey on 2017/7/25.
 * <p>
 * toast util
 */

public class ToastUtil {

    /**
     * show long time
     *
     * @param msg
     */
    public static void longShow(Context context, CharSequence msg) {
        CustomToast.makeText(context, msg == null ? "" : msg, Toast.LENGTH_LONG).show();
    }


    /**
     * show short time
     *
     * @param msg
     */
    public static void shortShow(Context context, CharSequence msg) {
        CustomToast.makeText(context, msg == null ? "" : msg, Toast.LENGTH_SHORT).show();
    }

}
