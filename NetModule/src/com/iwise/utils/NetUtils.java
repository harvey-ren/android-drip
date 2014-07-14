package com.iwise.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络相关的工具类
 * 
 * @author Harvey
 * 
 */
public class NetUtils
{

	/**
	 * 是否调试模式
	 */
	public static final boolean isDebug = true;

	/**
	 * 判断网络是否可用
	 * 
	 * @Title: isNetworkAvailable
	 * @Description:
	 * @param @param context
	 * @param @return
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean isNetworkAvailable(Context context)
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivityManager != null)
		{
			NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

			if (networkInfo != null && networkInfo.length > 0)
			{
				for (int i = 0; i < networkInfo.length; i++)
				{
					if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
					{
						return true;
					}
				}
			}
		}
		return false;
	}

}
