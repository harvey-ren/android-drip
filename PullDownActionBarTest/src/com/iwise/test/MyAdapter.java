/**  
 *
 * @File: MyAdapter.java
 * @Package com.iwise.test
 * @Description: 
 * @author Harvey  
 * @date 2014-7-3 下午2:41:19
 * 
 */
package com.iwise.test;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 自定义BaseAdapter
 * 
 * @ClassName: MyAdapter
 * @Description:
 * @author Harvey
 * @date 2014-7-3 下午5:59:26
 * 
 */
public class MyAdapter extends BaseAdapter {
	ArrayList<String> dataList = new ArrayList<String>();
	private LayoutInflater mInflater;
	private Context mContext;

	public MyAdapter(Context mContext) {
		this.mContext = mContext;
		this.mInflater = LayoutInflater.from(this.mContext);
	}

	public void putDataList(ArrayList<String> dataList) {
		this.dataList = dataList;
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		String data = dataList.get(position);
		System.out.println("data" + data);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			convertView.setTag(holder);
			holder.textView = (TextView) convertView
					.findViewById(R.id.textView);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.textView.setText(data);
		return convertView;
	}

	private static class ViewHolder {
		public TextView textView = null;

	}

}
