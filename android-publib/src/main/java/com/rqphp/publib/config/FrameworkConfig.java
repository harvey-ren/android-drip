package com.rqphp.publib.config;

public class FrameworkConfig {

    public static IFrameworkConfig mIFrameworkConfig;

    public static void setIFrameworkConfig(IFrameworkConfig iFrameworkConfig) {
        mIFrameworkConfig = iFrameworkConfig;
    }


    public static IFrameworkConfig getIFrameworkConfig() {
        return mIFrameworkConfig;
    }
}
