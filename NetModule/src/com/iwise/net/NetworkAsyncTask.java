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

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnShowListener;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iwise.activity.R;
import com.iwise.base.RZApplication;
import com.iwise.utils.CustomDialog;

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
public class NetworkAsyncTask extends AsyncTask<Request, Void, String>
{

	/**
	 * 超时时间
	 */
	private static final int REQUEST_TIME_OUT = 60000;

	/**
	 * 上下文
	 */
	private Context context;

	/**
	 * 响应监听
	 */
	private ResponseListener responseListener;

	/**
	 * 是否显示对话框
	 */
	private boolean isShowPromptDialog = false;

	/**
	 * 提示信息
	 */
	private String str_prompt_msg = "请稍候";

	/**
	 * 自定义对话框对象
	 */
	private CustomDialog progressDialog = null;

	/**
	 * 取消
	 */
	private static final String TASK_CANCEL = "task_cancel";

	/**
	 * 是否取消
	 */
	private boolean isCancelled = false;

	public NetworkAsyncTask(Context context)
	{
		isShowPromptDialog = false;
		this.context = context;
	}

	public NetworkAsyncTask(Context context, String str_msg)
	{
		isShowPromptDialog = true;
		str_prompt_msg = str_msg;
		this.context = context;
	}

	// 异步任务之前执行的方法
	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();

		if (isShowPromptDialog)
		{
			showProgressDialog();
		}
	}

	/**
	 * 显示进度对话框
	 * 
	 * @Title: showProgressDialog
	 * @Description:
	 * @param
	 * @return void 返回类型
	 * @throws
	 */

	private void showProgressDialog()
	{
		// 如果progressDialog为空
		if (progressDialog == null)
			progressDialog = new CustomDialog(context, R.layout.dialog_progress_custom, R.style.CustomDialog);

		// 设置ProgressDialog 是否可以按退回键取消
		progressDialog.setCancelable(true);

		// 设置ProgressDialog 不能点击屏幕取消的属性
		progressDialog.setCanceledOnTouchOutside(false);

		// 找到显示信息的文本
		TextView tv_msg = (TextView) progressDialog.findViewById(R.id.tv_msg);
		tv_msg.setText(str_prompt_msg);// 设置信息内容

		// 图片控件
		ImageView img_loading = (ImageView) progressDialog.findViewById(R.id.img_loading);
		img_loading.setBackgroundResource(R.anim.please_wait);

		final AnimationDrawable animationDrawable = (AnimationDrawable) img_loading.getBackground();

		progressDialog.setOnShowListener(new OnShowListener()
		{
			@Override
			public void onShow(DialogInterface dialog)
			{
				if (animationDrawable != null)
					animationDrawable.start();
			}
		});
		progressDialog.setOnCancelListener(new OnCancelListener()
		{
			@Override
			public void onCancel(DialogInterface dialog)
			{
				if (animationDrawable != null)
					animationDrawable.stop();
				onCancelled();
			}
		});
		progressDialog.show();
	}

	@Override
	protected void onCancelled()
	{
		super.onCancelled();
		isCancelled = true;
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

		if (isCancelled)
		{
			return TASK_CANCEL;
		}

		// 如果方法为POST方法
		if (method.equals(RequestMethod.POST.getMethodName()))
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

			// 设置参数
			httpPost.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, REQUEST_TIME_OUT);
			httpPost.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, REQUEST_TIME_OUT);

			// 添加请求头
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

			// 创建DefaultHttpClient对象
			DefaultHttpClient client = new DefaultHttpClient();

			if (isCancelled)
			{
				return TASK_CANCEL;
			}

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

			if (isCancelled)
			{
				if (httpPost != null)
				{
					// 释放资源
					httpPost.abort();
				}
				return TASK_CANCEL;
			}

			// 返回数据成功
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

	// 网络请求结束之后执行的方法
	@SuppressLint("ShowToast")
	@Override
	protected void onPostExecute(String result)
	{
		super.onPostExecute(result);

		if (progressDialog != null && progressDialog.isShowing())
		{
			progressDialog.dismiss();
		}

		if (result == null || result.equals(""))
		{
			if (!isCancelled)
			{
				Toast.makeText(context, "获取数据失败,请重试！", Toast.LENGTH_SHORT);
			}
			responseListener.onResponseFail();
		} else if (result.equals(TASK_CANCEL))
		{
			Toast.makeText(context, "任务取消！", Toast.LENGTH_SHORT);
		} else
		{
			System.out.println(result);
			responseListener.onResponseSuccess(ResponseParser.getInstance().parse(result));
		}
	}

	// 添加响应监听
	public void setOnResponseListener(ResponseListener responseListener)
	{
		this.responseListener = responseListener;
	}

}
