package com.rqphp.publib.dailog;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Window;
import android.widget.TextView;

import com.rqphp.publib.R;

/**
 * Created by Harvey on 2017/7/27.
 * dialog
 * 加载进度框
 */

public class LoadingDialog extends Dialog {

    private TextView title;

    public LoadingDialog(Context context) {
        this(context, R.style.CustomDialog);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    private void initView() {
        setContentView(R.layout.layout_loading);
        setCancelable(false);
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        title = window.findViewById(R.id.tv_loading_tip);
    }

    public void setCancle(boolean cancle) {
        setCancelable(cancle);
    }

    public void setTipText(String text) {
        String tip = TextUtils.isEmpty(text) ? "数据加载中..." : text;
        title.setText(tip);
    }

}
