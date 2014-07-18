package com.iwise.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * 
 * 发送广播的界面
 * 
 * @ClassName: OtherActivity
 * @Description:
 * @author Harvey
 * @date 2014-7-18 上午9:54:55
 * 
 */
public class OtherActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other);
	}

	public void send(View view)
	{
		Intent intent = new Intent();
		intent.setAction(MainActivity.TAG_ACTION);
		sendBroadcast(intent);
		OtherActivity.this.finish();
	}
}
