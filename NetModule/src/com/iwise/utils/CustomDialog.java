package com.iwise.utils;

import com.iwise.base.RZApplication;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;

/**
 * 自定义对话框
 * 
 * @author Harvey
 * 
 * 
 */
public class CustomDialog extends Dialog
{

	/**
	 * 宽度
	 */
	private static int DEFAULT_WIDTH = LayoutParams.MATCH_PARENT;

	/**
	 * 高度
	 */
	private static int DEFAULT_HEIGHT = LayoutParams.WRAP_CONTENT;

	/**
	 * 构造函数
	 * 
	 * @param context
	 *            上下文
	 * @param layout
	 *            布局文件id
	 * @param style
	 *            风格文件id
	 */
	public CustomDialog(Context context, int layout, int style)
	{
		this(context, DEFAULT_WIDTH, DEFAULT_HEIGHT, layout, style);
	}

	public CustomDialog(Context context, int width, int height, int layout, int style)
	{
		super(context, style);

		// set content
		// 加载布局文件
		setContentView(layout);

		// set window params
		// 创建Window对象
		Window window = getWindow();

		// 得到Window属性
		WindowManager.LayoutParams params = window.getAttributes();

		// set width,height by density and gravity
		// 得到密度数
		float density = RZApplication.getInstance().getScreenDensity();
		int screen_width = RZApplication.getInstance().getScreenWith();

		// 如果宽度小于0
		// 代码修改，MATCH_PARENT也会留出一个边
		if (width < 0)
		{
			params.width = (int) (screen_width - 40 * density);
		} else
		{
			params.width = (int) (width * density);
		}

		// 如果高度小于0
		if (height < 0)
		{
			params.height = DEFAULT_HEIGHT;
		} else
		{
			params.height = (int) (height * density);
		}

		// 居中显示
		params.gravity = Gravity.CENTER;

		// 将上面设置的参数添加到Window上
		window.setAttributes(params);
	}
}
