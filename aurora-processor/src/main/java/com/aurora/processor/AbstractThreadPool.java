package com.aurora.processor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yangjunwei
 * @date 2025/7/7 18:54
 */
public abstract class AbstractThreadPool {

    protected ThreadPoolExecutor executor;

    /**
     * 构造方法初始化
     */
    protected void init() {
        this.executor = initializeThreadPool();
    }

    /**
     * 初始化线程池
     * @return
     */
    protected abstract ThreadPoolExecutor initializeThreadPool();


    public void executeTask(Runnable task) {
        if (executor == null) {
            throw new IllegalStateException("ThreadPoolExecutor not initialized");
        }
        executor.execute(task);
    }

    // 关闭方法
    public void shutdown() {
        if (executor != null) {
            executor.shutdown();
        }
    }

    /**
     * 当前正在处理的任务数量
     *
     * @return
     */
    public int remainingCapacity() {
        return executor.getQueue().remainingCapacity();
    }


}
