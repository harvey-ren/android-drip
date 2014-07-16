package com.iwise.base;

/**
 * 对话框按钮的监听类
 * 
 * @File: DialogButtonListener.java
 * @Package com.iwise.base
 * @Description:
 * @author Harvey
 * @date 2014-7-14 下午4:08:34
 * 
 */
public abstract class DialogButtonListener
{
	/**
	 * 点击"确定"按钮
	 */
	public abstract void onPositiveButtonClick();

	/**
	 * 点击"取消"按钮
	 */
	public void onNegativeButtonClick()
	{
	}

}
