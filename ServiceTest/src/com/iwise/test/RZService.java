package com.iwise.test;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * 自写Service
 * 
 * @File: MyService.java
 * @Package com.iwise.test
 * @Description:
 * @author Harvey
 * @date 2014-7-16 下午1:48:05
 * 
 */
public class RZService extends Service
{

	private Timer timer = null;
	private TimerTask task = null;
	private int i = 0;

	@Override
	public void onCreate()
	{
		super.onCreate();
		System.out.println("onCreate()");
		startTimer();
	}

	/**
	 * 开始Timer
	 * 
	 * @Title: startTimer
	 * @Description:
	 * @param
	 * @return void 返回类型
	 * @throws
	 */
	private void startTimer()
	{
		if (timer == null)
		{
			timer = new Timer();
			task = new TimerTask()
			{
				@Override
				public void run()
				{
					i++;
					System.out.println(i);
				}
			};
			timer.schedule(task, 1000, 1000);
		}
	}

	/**
	 * 重写Binder
	 * 
	 * @ClassName: RZServiceBinder
	 * @Description:
	 * @author Harvey
	 * @date 2014-7-16 下午3:09:05
	 * 
	 */
	public class RZServiceBinder extends Binder
	{
		public RZService getService()
		{
			return RZService.this;
		}
	}

	private final RZServiceBinder serviceBinder = new RZServiceBinder();

	// 绑定时执行
	@Override
	public IBinder onBind(Intent intent)
	{
		System.out.println("onBind");
		return serviceBinder;
	}

	// 销毁
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		System.out.println("onDestroy()");
		stopTimer();
	}

	/**
	 * 停止Timer
	 * 
	 * @Title: stopTimer
	 * @Description:
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	private void stopTimer()
	{
		if (timer != null)
		{
			timer.cancel();
			task.cancel();

			task = null;
			timer = null;
		}
	}

}
