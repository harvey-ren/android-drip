package cn.iwise.base;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import cn.iwise.data.RZSharedPreferences;

public class RZApplication extends Application
{

	/**
	 * 全局实例
	 */
	private static RZApplication instance;

	@Override
	public void onCreate()
	{
		super.onCreate();
		instance = this;
		RZSharedPreferences.getInstance();
	}

	public static RZApplication getInstance()
	{
		return instance;
	}

	/**
	 * 版本号
	 * 
	 * @param context
	 * @return
	 */
	public int getAppVersionCode()
	{
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageManager packageManager = this.getPackageManager();

		PackageInfo packInfo;
		int versionCode = 0;
		try
		{
			packInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
			versionCode = packInfo.versionCode;
		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return versionCode;
	}

}
