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
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
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
		Request request = params[0];

		if (request == null)
			return str_result;

		String method = request.getRequestMethod().getMethodName();

		if (method == null || method.equals(""))
			return str_result;

		String url = request.getUrl();
		if (url == null || url.equals(""))
			return str_result;

		if (request.getRequestMethod().equals(RequestMethod.POST))
		{
			HttpPost httpPost = new HttpPost(url);
			List<BasicNameValuePair> valueList = new ArrayList<BasicNameValuePair>();
			if (request.getParams() == null || request.getParams().isEmpty())
				return str_result;
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

			DefaultHttpClient client = new DefaultHttpClient();

			HttpResponse httpResponse;

			try
			{
				httpResponse = client.execute(httpPost);
			} catch (IOException e)
			{
				if (httpPost != null)
				{
					// 释放资源
					httpPost.abort();
				}
				return str_result;
			}

			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				try
				{
					str_result = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);
				} catch (IOException e)
				{
					return str_result;
				}
			}
		}
		return str_result;
	}

	public void setOnResponseListener(ResponseListener responseListener)
	{

	}

}
