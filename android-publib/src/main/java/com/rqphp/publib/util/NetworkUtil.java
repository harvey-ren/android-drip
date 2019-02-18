package com.rqphp.publib.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by Harvey on 2017/7/24.
 */

public class NetworkUtil {


    /**
     * @param ipAddress
     * @return 其中参数-c 1是指ping的次数为1次，-w是指超时时间单位为s。
     * status 为0的时候表示网络可用，status等于2时表示当前网络不可用。
     */
    public static boolean pingIpAddress(String ipAddress) {
        try {
            Process process = Runtime.getRuntime().exec("/system/bin/ping -c 3 -w 1000 " + ipAddress);
            int status = process.waitFor();
            if (status == 0) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * network whether is available
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkAvailable(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable() && mNetworkInfo.isConnected();
        }
        return false;
    }


    /**
     * 是否连通网络
     *
     * @param context
     * @return
     */
    public static boolean isConntectNetWork(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo moblieNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (moblieNetworkInfo != null) {
            if (moblieNetworkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                return true;
            }
        }
        if (wifiNetworkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
            return true;
        }

//        if (moblieNetworkInfo.getState().equals(NetworkInfo.State.CONNECTED) || wifiNetworkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
//            return true;
//        }
        return false;
    }


    /**
     * 是否连接到wifi
     *
     * @param context
     * @return
     */
    public static boolean isConntectWifi(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
            return true;
        }
        return false;
    }


    /**
     * 获取本机ip
     */
    public static String getLocalIpAddress(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if (mNetworkInfo != null && mNetworkInfo.isConnected()) {
            if (mNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enIpAddr = intf.getInetAddresses(); enIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }
            } else if (mNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return "";
    }

    public static boolean IsIpv4(String ipv4) {
        if (ipv4 == null || ipv4.length() == 0) {
            return false;//字符串为空或者空串
        }
        String[] parts = ipv4.split("\\.");//因为java doc里已经说明, split的参数是reg, 即正则表达式, 如果用"|"分割, 则需使用"\\|"
        if (parts.length != 4) {
            return false;//分割开的数组根本就不是4个数字
        }
        for (int i = 0; i < parts.length; i++) {
            try {
                int n = Integer.parseInt(parts[i]);
                if (n < 0 || n > 255) {
                    return false;//数字不在正确范围内
                }
            } catch (NumberFormatException e) {
                return false;//转换数字不正确
            }
        }
        return true;
    }


    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }

    public static void setGprsEnable(Context context, boolean isEnable) {
        int result = 0;
        ConnectivityManager mCM = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            Class clazz = Class.forName(mCM.getClass().getName());
            Constructor[] cons = clazz.getDeclaredConstructors();
            Constructor con = clazz.getConstructor();//getDeclaredConstructors();
            con.setAccessible(true);

            Field iConnectivityManagerField = clazz.getDeclaredField("mService");
            iConnectivityManagerField.setAccessible(true);
            Object iConnectivityManager = iConnectivityManagerField.get(mCM);
            ConnectivityManager cm = (ConnectivityManager) con.newInstance(iConnectivityManager);
            Class[] argClasses = new Class[1];
            argClasses[0] = Boolean.class;
            Method ms = clazz.getDeclaredMethod("setMobileDataEnabled", argClasses);
            ms.setAccessible(true);
            Object obj = ms.invoke(cm, isEnable);
            result = (Integer) obj;
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取网速
     *
     * @param nowTotalBytes
     * @param lastTotalBytes
     * @param nowTimeStamp
     * @param lastTimeStamp
     * @return
     */
    public static String getNetWorkSpeed(long nowTotalBytes, long lastTotalBytes, long nowTimeStamp, long lastTimeStamp) {
        return formatNetWorkSpeed(((nowTotalBytes - lastTotalBytes) * 1000 / (nowTimeStamp - lastTimeStamp)));
    }

    /**
     * 格式化网速
     *
     * @param speed
     * @return
     */
    private static String formatNetWorkSpeed(long speed) {
        String strSpeed;
        if (speed > 1024) {
            strSpeed = String.valueOf(speed / 1024) + "kb/s";
        } else if (speed <= 1024 && speed > 0) {
            strSpeed = String.valueOf(speed) + "b/s";
        } else {
            strSpeed = "0b/s";
        }
        return strSpeed;
    }

    /**
     * 获取到总的字节数
     *
     * @param context
     * @return
     */
    public static long getTotalBytes(Context context) {
        // 得到整个手机的流量值
//        return TrafficStats.getUidRxBytes(context.getApplicationInfo().uid) == TrafficStats.UNSUPPORTED ? 0 : (TrafficStats.getTotalRxBytes() + TrafficStats.getTotalTxBytes());// 转为KB
        return TrafficStats.getUidRxBytes(context.getApplicationInfo().uid) == TrafficStats.UNSUPPORTED ? 0 : (TrafficStats.getTotalRxBytes());
        // 得到当前应用的流量值
//        return TrafficStats.getUidRxBytes (context.getApplicationInfo ().uid) == TrafficStats.UNSUPPORTED ? 0 : (TrafficStats.getUidRxBytes (context.getApplicationInfo ().uid) + TrafficStats.getUidTxBytes (context.getApplicationInfo ().uid));// 转为KB
    }

}
