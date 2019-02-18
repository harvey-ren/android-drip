package com.rqphp.publib.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.githang.statusbar.StatusBarCompat;
import com.rqphp.publib.R;
import com.rqphp.publib.config.FrameworkConfig;
import com.rqphp.publib.util.InputMethodUtil;
import com.rqphp.publib.util.ResourcesUtil;
import com.umeng.analytics.MobclickAgent;

/**
 * @author Harvey
 * @date 2018/7/24 13:54
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preSetContentView();
        setContentView(setContentViewId());
        postSetContentView();
        onInit();
    }

    protected void preSetContentView() {
    }

    protected void postSetContentView() {
        setStatusBarColor();
    }


    /**
     * 沉浸式状态栏
     */
    protected void setStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ResourcesUtil.getColor(this, getStatusBarColor()));
    }

    protected int getStatusBarColor() {
        int titleBgColor = FrameworkConfig.getIFrameworkConfig().getFrameworkResConfig().getTitleBgColor();
        if (titleBgColor == 0) {
            titleBgColor = R.color.color_white;
        }
        return titleBgColor;
    }

    /**
     * 设置布局文件
     *
     * @return
     */
    protected abstract int setContentViewId();

    /**
     * 初始化视图
     */
    protected abstract void onInit();

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                View view = getCurrentFocus();
                InputMethodUtil.hideSoftKeyboard(this, view, ev);
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(ev);

    }
}
