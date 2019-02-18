package com.rqphp.publib.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.rqphp.publib.base.BaseApplication;

/**
 *自定义EditText
 */
public class IEditText extends AppCompatEditText {


    private DrawableLeftListener mLeftListener;
    private DrawableRightListener mRightListener;


    public IEditText(Context context) {
        super(context);
    }

    public IEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDrawableLeftListener(DrawableLeftListener listener) {
        this.mLeftListener = listener;
    }

    public void setDrawableRightListener(DrawableRightListener listener) {
        this.mRightListener = listener;
    }

    public interface DrawableLeftListener {
        void onDrawableLeftClick(View view);
    }

    public interface DrawableRightListener {
        void onDrawableRightClick(View view);
    }


    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mRightListener != null) {
                    if (getCompoundDrawables()[2] != null) {
                        boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight()) && (event.getX() < ((getWidth() - getPaddingRight())));
                        if (touchable) {
                            try {
                                mRightListener.onDrawableRightClick(this);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if (mLeftListener != null) {
                    if (getCompoundDrawables()[0] != null) {
                        boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight()) && (event.getX() < ((getWidth() - getPaddingRight())));
                        if (touchable) {
                            try {
                                mLeftListener.onDrawableLeftClick(this);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        super.setTypeface(tf, style);
    }
}
