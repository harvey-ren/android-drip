package com.rqphp.publib.util;

import android.graphics.Paint;
import android.widget.TextView;

/**
 * Created by Harvey on 2017/10/26.
 */

public class TextViewUtil {


    /**
     * 添加下滑线
     *
     * @param textView
     */
    public static void setUnderline(TextView textView) {
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        textView.getPaint().setAntiAlias(true);//抗锯齿
    }


    /**
     * 添加中划线
     *
     * @param textView
     */
    public static void setStrike(TextView textView) {
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        textView.getPaint().setAntiAlias(true);//抗锯齿
    }
}
