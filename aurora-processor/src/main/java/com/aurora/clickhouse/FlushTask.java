package com.aurora.clickhouse;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.aurora.clickhouse.handler.ClickHouseInsertHandler;
import com.aurora.entity.BaseClickhouseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author AlbertYang
 */
public class FlushTask {

    protected static final Logger logger = LoggerFactory.getLogger(FlushTask.class);

    private final ClickHouseDataFlushType clickHouseDataFlushType;

    public FlushTask(ClickHouseDataFlushType clickHouseDataFlushType) {
        this.clickHouseDataFlushType = clickHouseDataFlushType;
    }

    /**
     * flush线程池
     */
    private static final ExecutorService EXECUTOR = ExecutorBuilder.create()
            .setAllowCoreThreadTimeOut(true)
            .setCorePoolSize(5)
            .setMaxPoolSize(10)
            .setKeepAliveTime(1, TimeUnit.MINUTES)
            .setWorkQueue(new LinkedBlockingQueue<>(ClickHouseDataFlushType.values().length))
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


    public void run(List<? extends BaseClickhouseData> data) {
        EXECUTOR.execute(() -> {
            //批量写入clickhouse
            long insertSize = SpringUtil.getBean(ClickHouseInsertHandler.class).batchInsert(clickHouseDataFlushType.getTableName(), data, clickHouseDataFlushType.getFlushSize());
            logger.debug(clickHouseDataFlushType.getTableName() + " insert to clickhouse {} items , success {} items", data.size(), insertSize);
        });
    }


}
