package com.iwise.net;

import java.util.HashMap;

/**
 * 请求管理类
 * 
 * @ClassName: RequestManager
 * @Description:管理请求的类
 * @author Harvey
 * @date 2014-7-14 下午1:10:09
 * 
 */
public class RequestManager
{

	private static RequestManager requestManager = null;

	/**
	 * 私有的构造方法
	 */
	private RequestManager()
	{
	}

	public static RequestManager getInstance()
	{
		if (requestManager == null)
		{
			requestManager = new RequestManager();
		}
		return requestManager;
	}

	/**
	 * 初始化接口
	 * 
	 * @Title: getInitRequest
	 * @Description:
	 * @param @param phonenumber 手机号码
	 * @param @param password 密码
	 * @param @param userid 用户id
	 * @param @param appversioncode
	 * @param @return 设定文件
	 * @return Request 返回类型
	 * @throws
	 */
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
