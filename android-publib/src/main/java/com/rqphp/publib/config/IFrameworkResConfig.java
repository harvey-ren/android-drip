package com.rqphp.publib.config;

import android.graphics.Typeface;

/**
 * 框架里面资源文件
 */
public interface IFrameworkResConfig {

    /**
     * 正常字体,可以是null
     *
     * @return
     */
    Typeface getNormalTypeface();


    /**
     * 加粗字体，可以是null
     *
     * @return
     */
    Typeface getBoldTypeface();


    /**
     * 相册名称
     *
     * @return
     */
    String getAlbumDirName();


    /**
     * @return
     */
    String getTempDirName();

    /**
     * 默认头像资源
     *
     * @return
     */
    int getDefaultAvatarRes();


    /**
     * 错误头像资源
     *
     * @return
     */
    int getErrorAvatarRes();


    /**
     * 主要颜色
     *
     * @return
     */
    int getPrimaryColor();


    /**
     * app 内部名称
     *
     * @return
     */
    String getAppInnerName();

    /**
     * 标题字体颜色
     *
     * @return
     */
    int getTitleTextColor();

    /**
     * 标题栏背景颜色
     *
     * @return
     */
    int getTitleBgColor();

    /**
     * 获取到返回按钮
     *
     * @return
     */
    int getBackIcon();
}
