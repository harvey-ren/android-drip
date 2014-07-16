package com.iwise.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Json工具类
 * 
 * @ClassName: JsonUtil
 * @Description: 
 * @author Harvey
 * @date 2014-7-16 上午10:32:24
 * 
 */
public class JsonUtil
{

	/**
	 * 获取到json对象
	 * 
	 * @param jsonObj
	 * @param key
	 * @param defaultobj
	 * @return
	 */
	public static JSONObject getJSONObject(JSONObject jsonObj, String key, JSONObject defaultobj)
	{
		if (jsonObj != null)
		{
			if (jsonObj.has(key))
			{
				try
				{
					return jsonObj.getJSONObject(key);
				} catch (JSONException e)
				{
					return defaultobj;
				}
			} else
			{
				return defaultobj;
			}
		}
		return defaultobj;
	}

	/**
	 * 获取数组
	 * 
	 * @param jsonObj
	 * @param key
	 * @param defaultArray
	 * @return
	 */
	public static JSONArray getJSONArray(JSONObject jsonObj, String key, JSONArray defaultArray)
	{
		if (jsonObj != null)
		{
			if (jsonObj.has(key))
			{
				try
				{
					return jsonObj.getJSONArray(key);
				} catch (JSONException e)
				{
					return defaultArray;
				}
			} else
			{
				return defaultArray;
			}
		}
		return defaultArray;
	}

	/**
	 * 获取key对应的值
	 * 
	 * @param resultJson
	 * @param key
	 * @param defaultStr
	 * @return
	 */
	public static String getString(JSONObject resultJson, String key, String defaultStr)
	{
		if (resultJson != null)
		{
			try
			{
				if (resultJson.has(key) && resultJson.getString(key) != null)
				{
					return resultJson.getString(key);
				} else
				{
					return defaultStr;
				}
			} catch (JSONException e)
			{
				return defaultStr;
			}
		}
		return defaultStr;
	}

	/**
	 * 返回json返回int
	 * 
	 * @param json
	 *            json对象
	 * @param name
	 *            key值
	 * @param defaultobj
	 *            默认返回值
	 * @return
	 */
	public static int getInt(JSONObject resultJson, String key, int defaultobj)
	{
		if (resultJson != null)
		{
			try
			{
				if (resultJson.has(key))
				{
					return resultJson.getInt(key);
				} else
				{
					return defaultobj;
				}
			} catch (JSONException e)
			{
				return defaultobj;
			}
		}
		return defaultobj;
	}

}
