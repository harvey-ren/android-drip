package com.rqphp.publib.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class IButton extends AppCompatButton {
    public IButton(Context context) {
        super(context);
    }

    public IButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void setTypeface(Typeface tf, int style) {
        super.setTypeface(tf, style);
    }
}
