package com.iwise.test;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

@SuppressLint("InlinedApi")
public class MainActivity extends Activity
{
	// 得到字符数组
	private String[] nameArr;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		nameArr = getResources().getStringArray(R.array.student);
		ArrayList<String> dataList = new ArrayList<String>();

		for (int i = 0; i < nameArr.length; i++)
		{
			dataList.add(nameArr[i]);
		}

		// 创建Adapter对象
		MyAdapter adapter = new MyAdapter(this);
		adapter.putDataList(dataList);

		// 得到ActionBar
		ActionBar actionBar = getActionBar();

		// 将ActionBar的操作模型设置为NAVIGATION_MODE_LIST
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		// 为ActionBar设置下拉菜单和监听器
		actionBar.setListNavigationCallbacks(adapter, new DropDownListenser());
	}

	/**
	 * 实现OnNavigationListener接口
	 */
	class DropDownListenser implements OnNavigationListener
	{
		// 当选择下拉菜单项的时候，将Activity中的内容置换为对应的Fragment
		public boolean onNavigationItemSelected(int itemPosition, long itemId)
		{
			// 创建FragmentManager对象
			FragmentManager manager = getFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			// 生成自定的Fragment
			PeopleFragment peopleFragment = new PeopleFragment();
			// 将Activity中的内容替换成对应选择的Fragment
			transaction.replace(R.id.context, peopleFragment, nameArr[itemPosition]);
			transaction.commit();
			return true;
		}
	}

}
