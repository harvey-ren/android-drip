package com.iwise.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.iwise.test.BitmapLoader.OnBitmapLoadFinishedListener;

public class NetWorkImageView extends ImageView
{
	private static BitmapLoader mBitmapLoader;
	private String lastUrl = "";
	private String alreadySetBitmapUrl = "";

	public NetWorkImageView(Context context)
	{
		super(context);
		init(context);
	}

	public NetWorkImageView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		init(context);
	}

	public NetWorkImageView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init(context);
	}

	private void init(Context context)
	{
		mBitmapLoader = BitmapLoader.getInstance(context);
	}

	public void loadBitmap(String url)
	{
		loadBitmap(url, false);
	}

	public void loadBitmap(String url, boolean isForceLoad)
	{
		if (TextUtils.isEmpty(url))
		{
			return;
		}

		this.lastUrl = url;

		if (isForceLoad ? true : !lastUrl.equals(alreadySetBitmapUrl))
		{
			Bitmap bitmap = mBitmapLoader.loadBitmap(lastUrl, new OnBitmapLoadFinishedListener()
			{
				@Override
				public void onBitmapLoadFinished(Bitmap imageBitmap, String imageUrl)
				{
					if (imageUrl.equals(lastUrl))
					{
						// setImageBitmap(imageBitmap);
						addImage(imageBitmap);
						alreadySetBitmapUrl = imageUrl;
					}
				}
			});
			if (bitmap != null)
			{
				addImage(bitmap);
				// setImageBitmap(bitmap);
				alreadySetBitmapUrl = lastUrl;
				return;
			} else
			{
				// È¡ÏûÄ¬ÈÏÍ¼Æ¬ by cui
				// setImageResource(Constants.DEFAULT_IMAGE_ID);
				// setProgressBar();
			}
		}
	}

	// private void setProgressBar()
	// {
	// Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
	// R.drawable.default_img);
	// double ratio = bitmap.getWidth() /
	// (FTApplication.getInstance().getScreenWith() * 1.0);
	// int scaledHeight = (int) (bitmap.getHeight() / ratio);
	// LinearLayout.LayoutParams params = new
	// LinearLayout.LayoutParams(FTApplication.getInstance().getScreenWith() -
	// 20, scaledHeight - 20);
	// params.gravity = Gravity.CENTER_HORIZONTAL;
	// setLayoutParams(params);
	// setBackgroundResource(R.drawable.default_img);
	// setScaleType(ScaleType.FIT_XY);
	// }

	protected void addImage(Bitmap bitmap)
	{
		double ratio = bitmap.getWidth() / (FTApplication.getInstance().getScreenWith() * 1.0);
		int scaledHeight = (int) (bitmap.getHeight() / ratio);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(FTApplication.getInstance().getScreenWith() - 20, scaledHeight - 20);
		params.gravity = Gravity.CENTER_HORIZONTAL;
		setLayoutParams(params);
		setImageBitmap(bitmap);
		setScaleType(ScaleType.FIT_XY);
	}

	public static void stopLoadFromNetWork()
	{
		BitmapLoader.isCanLoadFromNet = false;
	}

	public static void startLoadFromNetWork()
	{
		BitmapLoader.isCanLoadFromNet = true;
	}

}
