package com.rqphp.publib.view;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Harvey on 2018/1/15.
 */

public class ITextView extends AppCompatTextView {

    public ITextView(Context context) {
        super(context);
    }

    public ITextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ITextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        super.setTypeface(tf, style);
    }

    /**
     * 防止长按出错
     *
     * @return
     */
    @Override
    public boolean performLongClick() {
        return false;
    }
}
