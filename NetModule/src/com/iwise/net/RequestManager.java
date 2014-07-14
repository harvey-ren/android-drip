package com.iwise.net;

import java.util.HashMap;

/**
 * 请求管理类
 * 
 * @author Harvey
 * 
 */
public class RequestManager
{

	private static RequestManager requestManager = null;

	public static RequestManager getInstance()
	{
		if (requestManager == null)
		{
			requestManager = new RequestManager();
		}
		return requestManager;
	}

	public Request getInitRequest(String phonenumber, String password, String userid, int appversioncode)
	{
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("mobile", phonenumber);
		params.put("passwd", password);
		params.put("userid", userid);
		params.put("vercode", appversioncode + "");
		Request request = new Request(RequestUrl.INITINFO, params);
		return request;
	}

}
