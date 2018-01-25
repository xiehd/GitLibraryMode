package com.xiehd.git.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理类（线程池亦消耗资源，不要胡乱开启多个）
 * Created by Xiehd on 2018/1/25.
 */

public class ThreadPoolExecutorManage {

    public static int fixedNumber = 5;//固定线程池的大小
    public static int schedNumber = 2;//定时线程池的大小

    private static ExecutorService fixedThreadPool;
    private static ExecutorService singleThreadPool;
    private static ExecutorService cachedThreadPool;
    private static ScheduledExecutorService scheduledThreadPool;

    // private static ScheduledExecutorService singleThreadScheduledPool = Executors.newSingleThreadScheduledExecutor();

    /**
     * 在固定大小的线程池中运行
     *
     * @param mThread
     */
    public static void StartFixedThreadPool(Runnable mThread) {
        if (fixedThreadPool == null) {
            fixedThreadPool = Executors.newFixedThreadPool(fixedNumber);
        }
        fixedThreadPool.execute(mThread);
    }

    /**
     * 关闭固定大小的线程池
     *
     * @param type 0x00：在终止前允许执行以前提交的任务。
     *             0x01：阻止正在任务队列中等待任务的启动并试图停止当前正在执行的任务。
     */
    public static void CloseFixedThreadPool(int type) {
        if (fixedThreadPool != null) {
            if (type == 0x00) {
                fixedThreadPool.shutdown();
            } else if (type == 0x01) {
                fixedThreadPool.shutdownNow();
            }
            fixedThreadPool = null;
        }
    }

    /**
     * 在单个线程池中运行
     *
     * @param mThread
     */
    public static void StartSingleThreadPool(Runnable mThread) {
        if (singleThreadPool == null) {
            singleThreadPool = Executors.newSingleThreadExecutor();
        }
        singleThreadPool.execute(mThread);
    }

    /**
     * 关闭单个的线程池
     *
     * @param type 0x00：在终止前允许执行以前提交的任务。
     *             0x01：阻止正在任务队列中等待任务的启动并试图停止当前正在执行的任务。
     */
    public static void CloseSingleThreadPool(int type) {
        if (singleThreadPool != null) {
            if (type == 0x00) {
                singleThreadPool.shutdown();
            } else if (type == 0x01) {
                singleThreadPool.shutdownNow();
            }
            singleThreadPool = null;
        }
    }

    /**
     * 在自适应大小线程池中运行
     *
     * @param mThread
     */
    public static void StartCachedThreadPool(Runnable mThread) {
        if (cachedThreadPool == null) {
            cachedThreadPool = Executors.newCachedThreadPool();
        }
        cachedThreadPool.execute(mThread);
    }

    /**
     * 关闭自适应线程池
     *
     * @param type 0x00：在终止前允许执行以前提交的任务。
     *             0x01：阻止正在任务队列中等待任务的启动并试图停止当前正在执行的任务。
     */
    public static void CloseCachedThreadPool(int type) {
        if (cachedThreadPool != null) {
            if (type == 0x00) {
                cachedThreadPool.shutdown();
            } else if (type == 0x01) {
                cachedThreadPool.shutdownNow();
            }
            cachedThreadPool = null;
        }
    }

    /**
     * 在定时线程池中运行
     *
     * @param delaytime 延时多少秒后执行
     * @param mThread
     */
    private static void StartscheduledThreadPool(int delaytime, Runnable mThread) {
        if (scheduledThreadPool == null) {
            scheduledThreadPool = Executors.newScheduledThreadPool(schedNumber);
        }
        scheduledThreadPool.schedule(mThread, delaytime, TimeUnit.SECONDS);

    }

    /**
     * 在定时线程池中运行
     *
     * @param delaytime 延时多少秒后执行
     * @param time      每隔多少秒执行一次该任务
     * @param mThread
     */
    private static void StartscheduledThreadPool(int delaytime, int time, Runnable mThread) {
        if (scheduledThreadPool == null) {
            scheduledThreadPool = Executors.newScheduledThreadPool(schedNumber);
        }
        scheduledThreadPool.scheduleAtFixedRate(mThread, delaytime, time, TimeUnit.SECONDS);

    }

    /**
     * 关闭定时线程池
     *
     * @param type 0x00：在终止前允许执行以前提交的任务。
     *             0x01：阻止正在任务队列中等待任务的启动并试图停止当前正在执行的任务。
     */
    public static void ClosesCheduledThreadPool(int type) {
        if (scheduledThreadPool != null) {
            if (type == 0x00) {
                scheduledThreadPool.shutdown();
            } else if (type == 0x01) {
                scheduledThreadPool.shutdownNow();
            }
            scheduledThreadPool = null;
        }
    }

    /**
     * 清理关闭所有已经打开的线程池
     */
    public static void CleanThreadPool() {
        if (fixedThreadPool != null) {
            fixedThreadPool.shutdown();
            fixedThreadPool = null;
        }
        if (cachedThreadPool != null) {
            cachedThreadPool.shutdown();
            cachedThreadPool = null;
        }
        if (singleThreadPool != null) {
            singleThreadPool.shutdown();
            singleThreadPool = null;
        }
        if (scheduledThreadPool != null) {
            scheduledThreadPool.shutdown();
            scheduledThreadPool = null;
        }
    }


}
