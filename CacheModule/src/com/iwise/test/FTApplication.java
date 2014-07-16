package com.iwise.test;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 自定义 Application
 * 
 * @author Harvey
 * 
 */
public class FTApplication extends Application
{

	/**
	 * 全局实例
	 */
	private static FTApplication instance;

	/**
	 * 屏幕宽度
	 */
	private int screen_with = 0;

	/**
	 * 屏幕高度
	 */
	private int screen_height = 0;

	/**
	 * 屏幕密度
	 */
	private float screen_density = 0.0f;

	/**
	 * 记录所有被创建的Activity
	 */
	private ArrayList<Activity> activityList = new ArrayList<Activity>();

	/**
	 * android系统版本号
	 */
	public int sdkVersion;

	public static FTApplication getInstance()
	{
		return instance;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		instance = this;
		getScreenInfo();
		sdkVersion = Build.VERSION.SDK_INT;
	}

	private void getScreenInfo()
	{
		WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metric = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(metric);

		screen_with = metric.widthPixels; // 屏幕宽度（像素）
		screen_height = metric.heightPixels; // 屏幕高度（像素）
		screen_density = metric.density; // 屏幕密度（0.75 / 1.0 / 1.5）
		int densityDpi = metric.densityDpi; // 屏幕密度DPI（120 / 160 / 240）

		if (Utils.isDebug)
		{
			System.out.println("width===" + screen_with);
			System.out.println("height===" + screen_height);
			System.out.println("density===" + screen_density);
			System.out.println("densityDpi===" + densityDpi);
		}
	}

	public int getScreenWith()
	{
		return screen_with;
	}

	public int getScreenHeight()
	{
		return screen_height;
	}

	public float getScreenDensity()
	{
		return screen_density;
	}

	/**
	 * 版本号
	 * 
	 * @param context
	 * @return
	 */
	public int getAppVersionCode()
	{
		PackageManager packageManager = this.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo;
		int versionCode = 0;
		try
		{
			packInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
			versionCode = packInfo.versionCode;
		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
			return 1;
		}
		return versionCode;
	}

	/**
	 * 版本名
	 * 
	 * @param context
	 * @return
	 */
	public String getAppVersionName()
	{
		PackageManager packageManager = this.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		String versionName = "";
		try
		{
			PackageInfo packInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
			versionName = packInfo.versionName;
		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
			return "1.0.0";
		}
		return versionName;
	}

	/**
	 * 获取IMEI
	 * 
	 * @param context
	 * @return
	 */
	public String getIMEI()
	{
		String deviceId = "";
		TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		if (tm == null || tm.getDeviceId() == null || tm.getDeviceId().equals(""))
		{
			deviceId = getMacAddress(this);
			if (deviceId != null)
				deviceId.replace(":", "");
		} else
		{
			deviceId = tm.getDeviceId();
		}
		return deviceId;
	}

	/**
	 * 
	 * @param context
	 * @return
	 */
	private String getMacAddress(Context context)
	{
		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}

	/**
	 * 获取操作系统的版本
	 * 
	 * @return
	 */
	public String getOperation()
	{
		return android.os.Build.VERSION.RELEASE;
	}

	/**
	 * 获取手机型号
	 * 
	 * @return
	 */
	public String getPhoneModel()
	{
		return android.os.Build.MODEL;
	}

	/**
	 * 获取网络
	 * 
	 * @return
	 */
	public int getNetwork()
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivityManager == null)
			return 0;

		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

		if (networkInfo == null)
			return 0;
		return networkInfo.getType();
	}

	/**
	 * 获取网络类型
	 * 
	 * @return
	 */
	public int getNetworkTpye()
	{
		TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

		if (telephonyManager == null)
			return 0;

		return telephonyManager.getNetworkType();

	}

	/**
	 * 获取运营商名称
	 * 
	 * @return
	 */
	public String getOperatorName()
	{
		TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

		if (telephonyManager == null)
			return "未知";

		return telephonyManager.getSimOperatorName();

	}

	/**
	 * 添加Activity
	 * 
	 * @param activity
	 */
	public void addActivity(Activity activity)
	{
		if (activityList != null)
			activityList.add(activity);
	}

	/**
	 * 从列表中移除Activity
	 * 
	 * @param activity
	 */
	public void removeActivity(Activity activity)
	{
		if (activityList != null && !activityList.isEmpty())
			activityList.remove(activity);
	}

	@Override
	public void onTerminate()
	{
		super.onTerminate();
		if (activityList != null && !activityList.isEmpty())
		{
			// 关掉所有的Activity
			for (Activity activity : activityList)
			{
				if (activity != null)
				{
					activity.finish();
				}
			}
			// 清空装有所有Activity的list
			activityList.clear();
		}
		onDestroy();
	}

	/**
	 * 退出程序的操作
	 */
	protected void onDestroy()
	{
		Intent intent = new Intent(Intent.ACTION_MAIN);

		intent.addCategory(Intent.CATEGORY_HOME);

		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		startActivity(intent);

		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
