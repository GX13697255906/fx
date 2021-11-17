package com.fx.cloud.common.utils.thread;

import java.util.concurrent.*;

/**
 * 线程池工具
 *
 * @author guoxun
 */
public class ThreadPoolExecutorUtils {

    private ThreadPoolExecutorUtils() {
    }

    private static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

    private static final ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 2*Runtime.getRuntime().availableProcessors()+5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(8000), new ThreadPoolExecutor.AbortPolicy());

    public static ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }

    public static ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }
}
