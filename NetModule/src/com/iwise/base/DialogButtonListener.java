/**  
 *抽象类
 * @File: DialogButtonListener.java
 * @Package com.iwise.base
 * @Description: 
 * @author Harvey  
 * @date 2014-7-14 下午4:08:34
 * 
 */
package com.iwise.base;

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
