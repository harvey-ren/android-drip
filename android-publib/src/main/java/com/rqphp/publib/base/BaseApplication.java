package com.rqphp.publib.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.rqphp.publib.config.FrameworkConfig;
import com.rqphp.publib.config.IFrameworkConfig;
import com.rqphp.publib.util.LogUtil;
import com.rqphp.publib.util.ScreenUtil;
import com.rqphp.publib.util.ThreadManageUtil;


import java.util.Stack;


/**
 * BaseApplication
 * <p>
 * Created by Harvey on 2017/7/25.
 */

public abstract class BaseApplication extends Application {

    private static Stack<Activity> activityStack = new Stack<>();

    private static BaseApplication mInstance;

    public static BaseApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (mInstance == null) {
            mInstance = this;
        }
        ScreenUtil.initScreenInfo(this);
        FrameworkConfig.setIFrameworkConfig(getIFrameworkConfig(this));
    }

    public abstract IFrameworkConfig getIFrameworkConfig(Application application);

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


    /**
     * add activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        try {
            if (activityStack == null) {
                activityStack = new Stack<>();
            }
        } catch (Throwable throwable) {
            LogUtil.error(throwable.toString());
        }
        activityStack.add(activity);
    }

    /**
     * get current activity
     *
     * @return activity
     */
    public Activity currentActivity() {
        if (activityStack == null || activityStack.isEmpty()) {
            return null;
        }
        return activityStack.lastElement();
    }


    /**
     * is has assign activity
     *
     * @param cls activity
     * @return boolean
     */
    public boolean hasActivity(Class<?> cls) {
        if (cls == null || activityStack == null || activityStack.isEmpty()) {
            return false;
        }

        for (Activity activity : activityStack) {
            if (activity.equals(activity.getClass())) {
                return true;
            }
        }
        return false;
    }


    /**
     * finish assign activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            if (activityStack != null && !activityStack.isEmpty() && hasActivity(activity.getClass())) {
                activityStack.remove(activity);
            }

            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * finish assign class
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        if (activityStack != null && !activityStack.isEmpty()) {
            for (int i = activityStack.size() - 1; i >= 0; i--) {
                Activity activity = activityStack.get(i);
                if (activity != null) {
                    if (activity.getClass().equals(cls)) {
                        finishActivity(activity);
                    }
                }
            }
        }
    }


    /**
     * finish  all  activity
     */

    public void finishAllActivity() {
        if (activityStack != null && !activityStack.isEmpty()) {
            for (int i = activityStack.size() - 1; i >= 0; i--) {
                Activity activity = activityStack.get(i);
                if (activity != null) {
                    if (!activity.isFinishing()) {
                        activity.finish();
                    }
                }
            }
            activityStack.clear();
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        finishAllActivity();
    }
}
