package com.iwise.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.iwise.activity.R;

import com.iwise.net.NetworkAsyncTask;
import com.iwise.net.Request;
import com.iwise.net.ResponseListener;
import com.iwise.utils.NetUtils;

/**
 * 抽象类 基类
 * 
 * @author Harvey
 * 
 */
public abstract class BaseActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setLayoutXml();
		init();
		serviceLogic();
	}

	/**
	 * 设置布局文件
	 * 
	 * @Title: setLayoutXml
	 * @Description:设置布局文件
	 * @param
	 * @return void 返回类型
	 * @throws
	 */
	protected abstract void setLayoutXml();

	/**
	 * 初始化
	 * 
	 * @Title: init
	 * @Description:
	 * @param
	 * @return void 返回类型
	 * @throws
	 */
	protected void init()
	{
	}

	/**
	 * 业务逻辑
	 * 
	 * @Title: serviceLogic
	 * @Description:
	 * @param
	 * @return void 返回类型
	 * @throws
	 */
	protected void serviceLogic()
	{

	}

	/**
	 * 开启异步线程
	 * 
	 * @Title: startAsyncTask
	 * @Description:
	 * @param @param networkAsyncTask
	 * @param @param request
	 * @param @param responseListener 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	protected void startAsyncTask(NetworkAsyncTask networkAsyncTask, Request request, ResponseListener responseListener)
	{
		// 首先检查网络是否可用
		if (NetUtils.isNetworkAvailable(this))
		{
			networkAsyncTask.setOnResponseListener(responseListener);
			networkAsyncTask.execute(request);
		} else
		{
			showSettingNetWorkDialog();
		}
	}

	private void showSettingNetWorkDialog()
	{
		// 显示设置网络的对话框
		NetUtils.showDialog(this, getString(R.string.no_network_whether_set), new DialogButtonListener()
		{
			public void onPositiveButtonClick()
			{
				// 点击确定按钮跳转到设置网络界面
				startActivityForResult(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS), REQUESTCODE);
			}
		});
	}
}
