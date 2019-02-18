package com.rqphp.publib.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by Harvey on 2017/9/12.
 */

public class ThreadManageUtil {

    /**
     * 总共多少任务
     */
    private static final int count = 3;

    /**
     * 所有任务都一次性开始的线程池
     */
    private static ExecutorService mCacheThreadExecutor = null;

    /**
     * 每次执行限定个数个任务的线程池
     */
    private static ExecutorService mFixedThreadExecutor = null;

    /**
     * 创建一个可在指定时间里执行任务的线程池，亦可重复执行
     */
    private static ScheduledExecutorService mScheduledThreadExecutor = null;

    /**
     * 每次只执行一个任务的线程池
     */
    private static ExecutorService mSingleThreadExecutor = null;


    public static void init() {

        mCacheThreadExecutor = Executors.newCachedThreadPool();// 一个没有限制最大线程数的线程池

        mFixedThreadExecutor = Executors.newFixedThreadPool(count);// 限制线程池大小为count的线程池

        mScheduledThreadExecutor = Executors.newScheduledThreadPool(count);// 一个可以按指定时间可周期性的执行的线程池

        mSingleThreadExecutor = Executors.newSingleThreadExecutor();// 每次只执行一个线程任务的线程池
    }


    public static ExecutorService getCacheThreadExecutor() {
        return mCacheThreadExecutor;
    }


    public static ExecutorService getFixedThreadExecutor() {
        return mFixedThreadExecutor;
    }


    public static ScheduledExecutorService getScheduledThreadExecutor() {
        return mScheduledThreadExecutor;
    }

    public static ExecutorService getSingleThreadExecutor() {
        return mSingleThreadExecutor;
    }
}
