package cn.iwise.base;

import android.app.Activity;
import android.os.Bundle;

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
	 */
	protected abstract void setLayoutXml();

	/**
	 * 初始化
	 */
	protected void init()
	{
	}

	/**
	 * 业务逻辑
	 */
	protected void serviceLogic()
	{

	}
}
