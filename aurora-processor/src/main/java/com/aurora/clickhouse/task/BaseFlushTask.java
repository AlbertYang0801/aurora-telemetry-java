package com.aurora.clickhouse.task;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.aurora.clickhouse.ClickHouseInsertHandler;
import com.aurora.enums.ClickHouseDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author AlbertYang
 */
public abstract class BaseFlushTask<T> {

    protected static final Logger logger = LoggerFactory.getLogger(BaseFlushTask.class);

    protected BaseFlushTask() {
    }

    /**
     * flush线程池
     */
    private static final ExecutorService EXECUTOR = ExecutorBuilder.create()
            .setAllowCoreThreadTimeOut(true)
            .setCorePoolSize(5)
            .setMaxPoolSize(10)
            .setKeepAliveTime(1, TimeUnit.MINUTES)
            .setWorkQueue(new LinkedBlockingQueue<>(20))
            .setThreadFactory(ThreadUtil.newNamedThreadFactory("clickhouse-flush-job", false))
            .setHandler((r, executor) -> {
                try {
                    logger.warn("FlushJob Rejected Queue size :  {}", executor.getQueue());
                    executor.getQueue().put(r);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            })
            .build();


    public void run(List<T> data) {
        EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                //批量写入
                long insertSize = SpringUtil.getBean(ClickHouseInsertHandler.class).batchInsert(getClickHouseDataType().getTableName(), data, 500);
                logger.debug(getClickHouseDataType().getTableName() + " insert to clickhouse {} items , success {} items", data.size(), insertSize);
            }
        });
    }

    protected abstract ClickHouseDataType getClickHouseDataType();


}
