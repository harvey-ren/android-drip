package com.rqphp.publib.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

/**
 * Created by Harvey on 2017/10/26.
 */

public class ISwipeRefreshLayout extends SwipeRefreshLayout {

    public ISwipeRefreshLayout(Context context) {
        super(context);
    }

    public ISwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_orange_light);
    }
}
