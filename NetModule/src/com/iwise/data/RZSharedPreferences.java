package com.iwise.data;

import android.content.Context;
import android.content.SharedPreferences.Editor;

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

	/**
	 * 私有构造方法
	 * 
	 * Title:
	 * 
	 * Description:
	 * 
	 */
	private RZSharedPreferences()
	{
	}

	/**
	 * 单例
	 * 
	 * @Title: getInstance
	 * @Description: TODO
	 * @param @return 设定文件
	 * @return RZSharedPreferences 返回类型
	 * @throws
	 */
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
		return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(KEY_USERID, "2");
	}

	/**
	 * 设置用户id的值
	 * 
	 * @Title: putUserId
	 * @Description:
	 * @param @param context
	 * @param @param value 传入的值
	 * @return void 返回类型
	 * @throws
	 */
	public static void setUserId(Context context, String value)
	{
		Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
		editor.putString(KEY_USERID, value);
		editor.commit();
	}

	/**
	 * 获取手机号码
	 * 
	 * @param context
	 * @return
	 */
	public static String getPhoneNumber(Context context)
	{
		return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(KEY_PHONE_NUMBER, "13071108110");
	}

	/**
	 * 设置手机号码
	 * 
	 * @Title: setPhoneNumber
	 * @Description:
	 * @param @param context
	 * @param @param value 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void setPhoneNumber(Context context, String value)
	{
		Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
		editor.putString(KEY_PHONE_NUMBER, value);
		editor.commit();
	}

	/**
	 * 获取密码
	 * 
	 * @param context
	 * @return
	 */
	public static String getPassWord(Context context)
	{
		return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(KEY_PASSWORD, "123456");
	}

	/**
	 * 设置密码
	 * 
	 * @Title: setPassWord
	 * @Description:
	 * @param @param context
	 * @param @param value 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void setPassWord(Context context, String value)
	{
		Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
		editor.putString(KEY_PASSWORD, value);
		editor.commit();
	}

}
