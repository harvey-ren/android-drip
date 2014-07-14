package com.iwise.net;

import java.util.HashMap;

/**
 * «Î«Û¿‡
 * 
 * @author Harvey
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
