package com.aurora.processor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 接收消息 线程池拒绝策略
 * @author AlbertYang
 */
public class BlockReceiverRejectionHandler implements RejectedExecutionHandler {
    private static final Logger logger = LogManager.getLogger(BlockReceiverRejectionHandler.class);
    private final long warnCount;
    private final String executorName;
    private long rejectCount = 0;

    public BlockReceiverRejectionHandler(long warnCount, String executorName) {
        this.warnCount = warnCount;
        this.executorName = executorName;
    }
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if (!executor.isShutdown()) {
            try {
                rejectCount++;
                if (rejectCount == warnCount) {
                    logger.info("Thread pool queue of {} rejected another {} tasks." +
                            "Note: no data was lost.", executorName, rejectCount);
                    rejectCount = 0;
                }
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                // should not be interrupted
            }
        }
    }


}
