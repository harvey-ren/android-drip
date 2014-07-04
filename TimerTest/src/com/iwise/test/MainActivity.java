package com.iwise.test;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

/**
 * 
 * Timer的使用
 * 
 * @ClassName: MainActivity
 * @Description:主界面
 * @author Harvey
 * @date 2014-7-4 下午4:23:43
 * 
 */

public class MainActivity extends Activity
{

	public static final byte ENTER = 1;

	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		imageView = (ImageView) this.findViewById(R.id.imgView);

		// 创建Timer对象
		Timer timer = new Timer();

		// 创建TimerTask对象
		TimerTask task = new TimerTask()
		{
			@Override
			public void run()
			{
				// 创建Message对象
				Message message = new Message();
				message.what = ENTER;
				// 发送消息
				handler.sendMessage(message);
			}
		};
		if (timer != null)
		{
			timer.schedule(task, 2000);
		}
	}

	Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
				case ENTER:
					// 将图片置为不可见
					imageView.setVisibility(View.GONE);
					break;

				default:
					break;
			}
		};
	};

}
