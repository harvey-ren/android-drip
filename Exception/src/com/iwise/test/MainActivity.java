package com.iwise.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String data = " This content will append to the end of the file";
		File file = new File("abc.txt");

		FileWriter fileWritter;
		try
		{
			fileWritter = new FileWriter(file.getName(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(data);
			bufferWritter.close();
		} catch (IOException e)
		{
			System.out.println("---------IOException---------");
			return;
		} finally
		{
			System.out.println("---------finally---------");
		}

		System.out.println("---------Done---------");

	}

}
