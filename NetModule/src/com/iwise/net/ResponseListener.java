/**  
 *
 * @File: ResponseListener.java
 * @Package com.iwise.net
 * @Description: TODO(用一句话描述该文件做什么)
 * @author Harvey  
 * @date 2014-7-14 下午2:34:30
 * 
 */
package com.iwise.net;

public abstract class ResponseListener
{
	/**
	 * 响应成功
	 * 
	 * @param result
	 */
	public abstract void onResponseSuccess(NetWorkResponse response);

	/**
	 * 响应失败
	 */
	public void onResponseFail()
	{

	}
}
