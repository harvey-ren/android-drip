package com.example.fragmenttest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		TextView detail = (TextView) findViewById(R.id.tv_detail);
		detail.setText(getIntent().getExtras().getString("detail"));
	}
}
