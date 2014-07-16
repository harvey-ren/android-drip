package com.iwise.test;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

/**
 * 主界面
 * 
 * @ClassName: MainActivity
 * @Description:
 * @author Harvey
 * @date 2014-7-16 下午1:43:43
 * 
 */

public class MainActivity extends Activity implements ServiceConnection
{

	private Intent serviceIntent = null;
	private RZService service = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		serviceIntent = new Intent(this, RZService.class);
	}

	public void startService(View view)
	{
		System.out.println("startService");
		startService(serviceIntent);
	}

	public void stopService(View view)
	{
		System.out.println("stopService");
		stopService(serviceIntent);
	}

	public void bindService(View view)
	{
		System.out.println("bindService");
		bindService(serviceIntent, this, Context.BIND_AUTO_CREATE);
	}

	public void unbindService(View view)
	{
		System.out.println("unbindService");
		unbindService(this);
		service = null;
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder)
	{
		System.out.println("onServiceConnected");
		service = ((RZService.RZServiceBinder) binder).getService();
	}

	@Override
	public void onServiceDisconnected(ComponentName name)
	{

	}

}
