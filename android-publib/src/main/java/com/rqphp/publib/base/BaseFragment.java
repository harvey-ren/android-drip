package com.rqphp.publib.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Fragment 基类
 * Created by Harvey on 2017/11/10.
 */

public abstract class BaseFragment extends Fragment {

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(setLayoutResId(), container, false);
        onInit(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 设置布局文件
     *
     * @return 布局文件id
     */
    protected abstract int setLayoutResId();


    /**
     * 初始化
     */
    protected abstract void onInit(View view);


    /**
     *
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
