/**
 * 
 * 异步加载类
 * 
 * @File: NetworkAsyncTask.java
 * @Package com.iwise.net
 * @Description:
 * @author Harvey
 * @date 2014-7-14 下午2:21:39
 * 
 */
package com.iwise.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import com.iwise.base.RZApplication;

public class NetworkAsyncTask extends AsyncTask<Request, Void, String>
{

	/**
	 * 超时时间
	 */
	private static final int REQUEST_TIME_OUT = 60000;

	private Context context;

	private ResponseListener responseListener;

	public NetworkAsyncTask(Context context)
	{
		this.context = context;
	}

	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(Request... params)
	{
		String str_result = "";

		// 得到Request对象
		Request request = params[0];
		if (request == null)
			return str_result;

		// 得到url地址
		String url = request.getUrl();
		if (url == null || url.equals(""))
			return str_result;

		// 得到的请求方法
		String method = request.getRequestMethod().getMethodName();
		if (method == null || method.equals(""))
			return str_result;

		// 如果方法为POST方法
		if (method.equals(RequestMethod.POST))
		{
			// 创建HttpPost对象
			HttpPost httpPost = new HttpPost(url);

			// 集合
			List<BasicNameValuePair> valueList = new ArrayList<BasicNameValuePair>();

			// 如果请求参数是空的
			if (request.getParams() == null || request.getParams().isEmpty())
				return str_result;

			// 遍历添加参数
			for (Map.Entry<String, String> param : request.getParams().entrySet())
			{
				valueList.add(new BasicNameValuePair(param.getKey(), param.getValue()));
			}
			try
			{
				httpPost.setEntity(new UrlEncodedFormEntity(valueList, HTTP.UTF_8));
			} catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
				return str_result;
			}
			
			httpPost.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, REQUEST_TIME_OUT);
			httpPost.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, REQUEST_TIME_OUT);

			httpPost.setHeader("Version", RZApplication.getInstance().getAppVersionName());
			httpPost.setHeader("Imei", RZApplication.getInstance().getIMEI());
			httpPost.setHeader("Operation", RZApplication.getInstance().getOperation());
			httpPost.setHeader("Deviceid", RZApplication.getInstance().getPhoneModel() + "<>" + Build.BRAND);
			httpPost.setHeader("Network", RZApplication.getInstance().getNetwork() + "");
			httpPost.setHeader("Nettype", RZApplication.getInstance().getNetworkTpye() + "");
			httpPost.setHeader("Opname", RZApplication.getInstance().getOperatorName());
			httpPost.setHeader("Devicewidth", RZApplication.getInstance().getScreenWith() + "");
			httpPost.setHeader("Deviceheight", RZApplication.getInstance().getScreenHeight() + "");
			httpPost.setHeader("User-Agent", Header.USER_AGENT);
			httpPost.setHeader("Platform", Header.PLATFORM);
			httpPost.setHeader("Authid", Header.AUTHID);
			httpPost.setHeader("Channelcode", Header.CANNEL_CODE);
			httpPost.setHeader("Type", Header.APP_TYPE);
			httpPost.setHeader("Timestamp", System.currentTimeMillis() + "");

			// 创建DefaultHttpClient
			DefaultHttpClient client = new DefaultHttpClient();

			HttpResponse httpResponse;

			try
			{
				httpResponse = client.execute(httpPost);
			} catch (IOException e)
			{
				if (httpPost != null)
				{
					httpPost.abort();// 释放资源
				}
				return str_result;
			}

			//
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				try
				{
					str_result = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}

			if (httpPost != null)
			{
				httpPost.abort();// 释放资源
			}
		}
		return str_result;
	}

	@Override
	protected void onPostExecute(String result)
	{
		super.onPostExecute(result);
		responseListener.onResponseSuccess(ResponseParser.getInstance().parse(result));
	}

	public void setOnResponseListener(ResponseListener responseListener)
	{
		this.responseListener = responseListener;
	}

}
