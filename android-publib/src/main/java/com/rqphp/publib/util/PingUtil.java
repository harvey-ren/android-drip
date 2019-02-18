package com.rqphp.publib.util;

import android.os.Handler;
import android.os.Message;

import com.rqphp.publib.listener.PingListener;


/**
 * Created by Harvey on 2017/10/26.
 * <p>
 * ping 工具
 */

public class PingUtil {

    private PingListener mListener;

    public PingUtil(PingListener listener) {
        mListener = listener;
        ThreadManageUtil.getFixedThreadExecutor().execute(new PingRunnable());
    }

    class PingRunnable implements Runnable {

        @Override
        public void run() {
            boolean isConnect = NetworkUtil.pingIpAddress("www.baidu.com");
            mHandler.sendMessage(mHandler.obtainMessage(0, isConnect));
        }
    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mListener.onPing((Boolean) msg.obj);
        }
    };
}
