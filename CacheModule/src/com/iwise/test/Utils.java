package com.iwise.test;

import java.io.File;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;

public class Utils
{
	public static final boolean isDebug = true;

	/**
	 * 程序数据保存文件夹
	 */
	public static final String CACHE_DATA_DIR = "/defender";

	/**
	 * 该影集所有选中图片地址的集合
	 */
	public static ArrayList<String> list_str_selected_photo_path = new ArrayList<String>();

	public static ArrayList<String> list_str_photo_path = new ArrayList<String>();

	/**
	 * 产品名称
	 */
	public static String GOODS_NAME = "";

	/**
	 * 网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public synchronized static boolean isNetworkAvailable(Context context)
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager == null)
			return false;

		NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

		if (networkInfo != null && networkInfo.length > 0)
		{
			for (int i = 0; i < networkInfo.length; i++)
			{
				if (isDebug)
				{
					System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
					System.out.println(i + "===状态===" + networkInfo[i].getState());
				}

				// 判断当前网络状态是否为连接状态
				if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断SDCard是否安装好
	 * 
	 * @return
	 */
	public static boolean isSDCardMounted()
	{
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			return true;
		}
		return false;
	}

	public static String getFileCacheDirectory()
	{
		File sdcardDir = Environment.getExternalStorageDirectory();
		String fileDirectory = "/mnt/sdcard";
		if (null != sdcardDir)
		{
			fileDirectory = sdcardDir.getAbsolutePath() + CACHE_DATA_DIR;
		}
		return fileDirectory;
	}

	public static File getSDCardPath()
	{
		String path = "";
		File file = null;

		// 如果SDCard处于正常的状态
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			path = Environment.getExternalStorageDirectory().getPath() + CACHE_DATA_DIR;
			file = new File(path);
			if (!file.exists())
			{
				file.mkdirs();
				if (isDebug)
					Log.i("crate app data dir", "" + file.getAbsolutePath());
			}
			return file;
		} else
		{
			return null;
		}
	}

	/**
	 * 格式化日期
	 * 
	 * @param dateStr
	 * @return
	 */

	@SuppressLint("SimpleDateFormat")
	public static String formatDate(String dateStr)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		String new_date_str = "";
		try
		{
			date = sdf.parse(dateStr);
			new_date_str = sdf.format(date);
		} catch (ParseException e)
		{
			e.printStackTrace();
			return dateStr;
		}

		return new_date_str;
	}

	public static void log(String log)
	{
		if (isDebug)
		{
			Log.i(Constants.TAG, log);
		}
	}

	public static String MD5(String source) throws Exception
	{
		String resultHash = null;
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] result = new byte[md5.getDigestLength()];
		md5.reset();
		md5.update(source.getBytes("UTF-8"));
		result = md5.digest();

		StringBuffer buf = new StringBuffer(result.length * 2);

		for (int i = 0; i < result.length; i++)
		{
			int intVal = result[i] & 0xff;
			if (intVal < 0x10)
			{
				buf.append("0");
			}
			buf.append(Integer.toHexString(intVal));
		}

		resultHash = buf.toString();

		return resultHash.toString();
	}
}
