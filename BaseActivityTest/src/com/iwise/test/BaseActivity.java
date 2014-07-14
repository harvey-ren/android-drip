package com.iwise.test;

import android.app.Activity;
import android.os.Bundle;

/**
 * 基类
 * 
 * @ClassName: BaseActivity
 * @Description: 创建自己的基类
 * @author Harvey
 * @date 2014-7-14 上午9:35:04
 * 
 */
public abstract class BaseActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setLayoutView();
		initialize();
		serviceLogic();
	}

	/**
	 * 设置布局文件
	 * 
	 * @Title: setLayoutView
	 * @Description: 设置布局文件
	 * @param
	 * @return void 返回类型
	 * @throws
	 */
	protected abstract void setLayoutView();

	/**
	 * 初始化
	 * 
	 * @Title: initialize
	 * @Description:
	 * @param
	 * @return void 返回类型
	 * @throws
	 */
	protected void initialize()
	{

	}

	/**
	 * 业务逻辑方法
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

}
