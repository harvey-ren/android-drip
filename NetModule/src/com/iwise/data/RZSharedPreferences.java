package com.iwise.data;

import android.content.Context;

/**
 * SharedPreferences类
 * 
 * @ClassName: RZSharedPreferences
 * @Description:SharedPreferences类
 * @author Harvey
 * @date 2014-7-14 上午11:12:32
 * 
 */
public class RZSharedPreferences
{

	/**
	 * RZSharedPreferences实例
	 */
	private static RZSharedPreferences instance = null;

	/**
	 * 文件名称
	 */
	private static final String FILE_NAME = "network_module";

	/**
	 * 用户id的Key值
	 */
	private static final String KEY_USERID = "userid";

	/**
	 * 用户手机号码的Key值
	 */
	private static final String KEY_PHONE_NUMBER = "phone_number";

	/**
	 * 密码的Key值
	 */
	private static final String KEY_PASSWORD = "password";

	// 私有构造方法
	private RZSharedPreferences()
	{
	}

	// 单例
	public static RZSharedPreferences getInstance()
	{
		if (instance == null)
		{
			instance = new RZSharedPreferences();
		}
		return instance;
	}

	/**
	 * 获取用户的id
	 * 
	 * @param context
	 * @return
	 */
	public static String getUserId(Context context)
	{
		return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(KEY_USERID, "");
	}

	/**
	 * 获取手机号码
	 * 
	 * @param context
	 * @return
	 */
	public static String getPhoneNumber(Context context)
	{
		return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(KEY_PHONE_NUMBER, "");
	}

	/**
	 * 获取密码
	 * 
	 * @param context
	 * @return
	 */
	public static String getPassWord(Context context)
	{
		return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(KEY_PASSWORD, "");
	}

}
