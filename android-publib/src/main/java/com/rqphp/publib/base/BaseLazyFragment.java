package com.rqphp.publib.base;

import android.os.Bundle;
import android.support.annotation.UiThread;

/**
 * Created by Administrator on 2017/12/29.
 */

public abstract class BaseLazyFragment extends BaseFragment {
    /**
     * 懒加载过
     */
    private boolean isLazyLoaded;

    private boolean isPrepared;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }

    /**
     * 调用懒加载
     */
    private void lazyLoad() {
        if (getUserVisibleHint() && isPrepared && !isLazyLoaded) {
            onLazyLoad();
            isLazyLoaded = true;
        }
    }

    @UiThread
    public abstract void onLazyLoad();

}
