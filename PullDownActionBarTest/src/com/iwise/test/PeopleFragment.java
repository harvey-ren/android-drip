/**  
 *
 *
 * @File: StudentFragment.java
 * @Package com.iwise.test
 * @Description: 
 * @author Harvey  
 * @date 2014-7-3 下午2:11:00
 * 
 */
package com.iwise.test;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PeopleFragment extends Fragment
{
	// Fragment对应的标签，当Fragment依附于Activity时得到
	private String tag;

	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		tag = getTag();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		TextView textView = new TextView(getActivity());
		textView.setText(tag);
		return textView;
	}
}
