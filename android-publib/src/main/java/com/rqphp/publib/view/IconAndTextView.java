package com.rqphp.publib.view;


import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rqphp.publib.R;


/**
 * Created by Harvey on 2017/9/8.
 */

public class IconAndTextView extends LinearLayout {

    private LayoutInflater mInflater;
    private TextView mTextView;
    private ImageView mImgView;

    public IconAndTextView(Context context) {
        this(context, null);
    }

    public IconAndTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconAndTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        initView();
    }

    private void initView() {
        View view = mInflater.inflate(R.layout.view_icon_text, this, true);
        mImgView = view.findViewById(R.id.imgView);
        mTextView = view.findViewById(R.id.textView);
    }


    public void setTexSize(float size) {
        mTextView.setTextSize(size);
    }


    public void setTexColor(int color) {
        mTextView.setTextColor(color);
    }

    public void setData(int resid, String title) {
        if (resid == 0 || TextUtils.isEmpty(title))
            return;
        mImgView.setImageResource(resid);
        mTextView.setText(title);
    }

}
