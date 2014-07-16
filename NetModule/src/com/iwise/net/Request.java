package com.iwise.net;

import java.util.HashMap;

/**
 * 请求对象类
 * 
 * @ClassName: Request
 * @Description:
 * @author Harvey
 * @date 2014-7-16 上午10:23:50
 * 
 */
public class Request
{
	private String url;
	private HashMap<String, String> params = new HashMap<String, String>();
	private RequestMethod method = RequestMethod.POST;

	public Request(String url, HashMap<String, String> params)
	{
		this.url = url;
		this.params = params;
	}

	public Request(String url, HashMap<String, String> params, RequestMethod method)
	{
		this(url, params);
		this.method = method;
	}

	public String getUrl()
	{
		return this.url;
	}

	public HashMap<String, String> getParams()
	{
		return this.params;
	}

	public RequestMethod getRequestMethod()
	{
		return this.method;
	}
}
