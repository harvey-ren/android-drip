package com.iwise.test;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * BroadcastReceiver使用方法
 * 
 * @ClassName: MainActivity
 * @Description:
 * @author Harvey
 * @date 2014-7-17 上午10:34:34
 * 
 */
public class MainActivity extends Activity
{

	public static final String TAG_ACTION = "action";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		registerBoradcastReceiver();
		setContentView(R.layout.main);
	}

	/**
	 * 注册广播
	 * 
	 * @Title: registerBoradcastReceiver
	 * @Description:
	 * @param
	 * @return void 返回类型
	 * @throws
	 */
	private void registerBoradcastReceiver()
	{
		IntentFilter mIntentFilter = new IntentFilter();
		mIntentFilter.addAction(TAG_ACTION);
		registerReceiver(receiver, mIntentFilter);
	}

	// 广播接收者 实例
	BroadcastReceiver receiver = new BroadcastReceiver()
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			if (intent == null)
				return;
			if (intent.getAction() == null || intent.getAction().equals(""))
				return;

			if (intent.getAction().equals(TAG_ACTION))
			{
				Toast.makeText(MainActivity.this, "接收到了广播", Toast.LENGTH_SHORT).show();
			}
		}
	};

	/**
	 * 跳转界面的方法
	 * 
	 * @Title: jump
	 * @Description:
	 * @param @param view 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void jump(View view)
	{
		Intent intent = new Intent();
		intent.setClass(this, OtherActivity.class);
		startActivity(intent);
	}

	// 解除绑定
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		if (receiver != null)
			unregisterReceiver(receiver);
	}

}
