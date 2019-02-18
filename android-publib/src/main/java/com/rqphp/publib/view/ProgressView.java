package com.rqphp.publib.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * 进度框
 *
 * @author Harvey
 */
public class ProgressView extends ProgressBar {
    public ProgressView(Context context) {
        super(context);
        setCircleColor();
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCircleColor();
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCircleColor();
    }

    public void setCircleColor() {
        getIndeterminateDrawable().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
    }
}
