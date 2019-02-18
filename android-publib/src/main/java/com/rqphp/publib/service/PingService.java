package com.rqphp.publib.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.IBinder;

import com.rqphp.publib.listener.PingListener;
import com.rqphp.publib.util.PingUtil;

/**
 * ping 服务
 * Created by Harvey on 2017/11/8.
 */

public class PingService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        pingNet();
    }

    private void pingNet() {
        new PingUtil(new PingListener() {
            @Override
            public void onPing(boolean isConnectInternet) {
                if (!isConnectInternet) {
                    ConnectivityManager connectivityManager = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo mNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (mNetworkInfo != null && mNetworkInfo.isConnected()) {
                        if (mNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                            wifiManager.setWifiEnabled(false);
                        }
                    }
                }
                stopSelf();
            }
        });
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
