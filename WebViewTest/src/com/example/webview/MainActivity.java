package com.example.webview;

import com.example.webdemo.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * 
 * WebView的使用方法
 * 
 * @ClassName: MainActivity
 * @Description:
 * @author Harvey
 * @date 2014-7-1 下午1:27:41
 * 
 */
public class MainActivity extends Activity
{
	private WebView webView;
	private ProgressBar progressBar;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		webView = (WebView) this.findViewById(R.id.webview);
		progressBar = (ProgressBar) this.findViewById(R.id.progressBar);

		// 设置执行能不能执行JavaScript
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setSupportZoom(true);
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webView.requestFocus();

		webView.loadUrl("http://leyu.miao.cn/weixin/index/list.jhtml");

		webView.setWebViewClient(new WebViewClient()
		{
			// 网页加载开始时调用，显示进度条
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon)
			{
				super.onPageStarted(view, url, favicon);
				progressBar.setVisibility(View.VISIBLE);
			}

			// 网页加载完成时调用，隐藏进度条
			@Override
			public void onPageFinished(WebView view, String url)
			{
				super.onPageFinished(view, url);
				progressBar.setVisibility(View.GONE);
			}

			// 网页加载失败时调用，隐藏进度条
			@Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
			{
				super.onReceivedError(view, errorCode, description, failingUrl);
				progressBar.setVisibility(View.GONE);
			}

			// 在当前的WebView中跳转到新的url
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url)
			{
				view.loadUrl(url);
				return true;
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
			case KeyEvent.KEYCODE_BACK:
				webView.goBack();
				return true;

			default:
				break;
		}
		return super.onKeyDown(keyCode, event);
	}
}
