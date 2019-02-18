package com.rqphp.publib.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rqphp.publib.R;


/**
 * 自定义Toast
 * Created by Harvey on 2017/11/10.
 */

public class CustomToast extends Toast {


    /**
     * 构造方法
     *
     * @param context
     */
    private CustomToast(Context context, CharSequence text, int duration) {
        super(context);
        initView(context, text, duration);
    }


    /**
     * 初始化布局
     *
     * @param context
     * @param text
     * @param duration
     */
    private void initView(Context context, CharSequence text, int duration) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
        TextView content = view.findViewById(R.id.tv_content);
        content.setText(text);
        setDuration(duration);
        setView(view);
    }


    /**
     * 创建新的Toast
     *
     * @param context
     * @param text
     * @param duration
     * @return
     */
    public static Toast makeText(Context context, CharSequence text, int duration) {
        CustomToast customToast = new CustomToast(context, text, duration);
        return customToast;
    }
}
