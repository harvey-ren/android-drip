package com.iwise.activity;

import android.view.View;
import cn.iwise.activity.R;

import com.iwise.base.BaseActivity;
import com.iwise.base.RZApplication;
import com.iwise.data.RZSharedPreferences;
import com.iwise.net.NetWorkResponse;
import com.iwise.net.NetworkAsyncTask;
import com.iwise.net.Request;
import com.iwise.net.RequestManager;
import com.iwise.net.ResponseListener;
import com.iwise.utils.NetUtils;

/**
 * 主界面类
 * 
 * @author Harvey
 * 
 */
public class MainActivity extends BaseActivity
{
	@Override
	protected void setLayoutXml()
	{
		setContentView(R.layout.main);
	}

	/**
	 * 请求网络的方法
	 * 
	 * @param view
	 */
	public void request(View view)
	{
		if (NetUtils.isDebug)
			System.out.println("request方法被执行>>>>>>>>>>");
		if (NetUtils.isNetworkAvailable(this))
		{
			String userid = RZSharedPreferences.getUserId(this);
			String phonenumber = RZSharedPreferences.getPhoneNumber(this);
			String password = RZSharedPreferences.getPassWord(this);
			Request request = RequestManager.getInstance().getInitRequest(phonenumber, password, userid, RZApplication.getInstance().getAppVersionCode());
			NetworkAsyncTask networkAsyncTask = new NetworkAsyncTask(this);
			startAsyncTask(networkAsyncTask, request, new ResponseListener()
			{
				@Override
				public void onResponseSuccess(NetWorkResponse response)
				{

				}
			});
		}
	}
}
