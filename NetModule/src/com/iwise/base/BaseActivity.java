package com.iwise.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.iwise.activity.R;
import com.iwise.utils.NetUtils;

/**
 * 基类
 * 
 * @ClassName: BaseActivity
 * @Description:
 * @author Harvey
 * @date 2014-7-16 上午10:24:50
 * 
 */
public abstract class BaseActivity extends Activity
{

	protected byte REQUESTCODE = 0;

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
	 * 显示设置网路的提示对话框
	 * 
	 * @Title: showSettingNetWorkDialog
	 * @Description:
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	protected void showSettingNetWorkDialog()
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
