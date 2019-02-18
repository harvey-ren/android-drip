package com.rqphp.publib.listener;


import android.view.MotionEvent;
import android.view.View;

/**
 * 多次点击
 * Created by Harvey on 2017/9/13.
 */

public class OnMultipleClickListener implements View.OnTouchListener {

    private int tempCount = 0;

    private static final int INTERVAL = 500;

    private CallBack mCallBack;

    private long[] clickTimes;

    private int mCount = 0;

    public interface CallBack {
        void onClick();
    }

    public OnMultipleClickListener(CallBack callBack, int count) {
        super();
        mCallBack = callBack;
        mCount = count;
        clickTimes = new long[mCount];
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            tempCount++;
            if (tempCount == 1) {
                clickTimes[0] = System.currentTimeMillis();
            } else if (tempCount > 1) {
                long tempCurTime = System.currentTimeMillis();
                if (tempCurTime - clickTimes[tempCount - 2] < INTERVAL) {
                    clickTimes[tempCount - 1] = tempCurTime;
                } else {
                    tempCount = 1;
                    clickTimes[0] = System.currentTimeMillis();
                }
            }

            if (tempCount == mCount) {
                if (mCallBack != null) {
                    mCallBack.onClick();
                }
                tempCount = 0;
            }
        }
        return true;
    }
}
