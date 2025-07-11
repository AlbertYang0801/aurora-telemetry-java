package com.aurora.clickhouse.buffer;

import cn.hutool.core.collection.CollUtil;
import com.aurora.clickhouse.ClickHouseDataFlushType;
import com.aurora.clickhouse.FlushTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * buffer
 * 定期和定时写入
 *
 * @author AlbertYang
 */
public class DataBuffer<T> {
    public static final Logger logger = LoggerFactory.getLogger(DataBuffer.class);

    private final ScheduledExecutorService executorService;

    private final ArrayBlockingQueue<T> queue;

    /**
     * 检测时间
     */
    private final AtomicInteger elapsedTime;

    private final FlushTask<T> flushTask;

    private final int flushSize;

    private final int flushTime;

    public DataBuffer(ClickHouseDataFlushType clickHouseDataFlushType) {
        this.flushTask = new FlushTask<>(clickHouseDataFlushType);
        this.flushSize = clickHouseDataFlushType.getFlushSize();
        this.flushTime = clickHouseDataFlushType.getFlushTimeSeconds();
        this.queue = new ArrayBlockingQueue<>(flushSize * 2);
        executorService = Executors.newSingleThreadScheduledExecutor();
        elapsedTime = new AtomicInteger(0);
        //开启定时线程
        startScheduleProcessBuffer();
    }

    /**
     * @param flushSize        队列达到批次大小进行刷新
     * @param flushTimeSeconds 经过指定时间写入数据
     */
    public DataBuffer(FlushTask<T> flushTask, int flushSize, int flushTimeSeconds) {
        this.flushTask = flushTask;
        this.flushSize = flushSize;
        this.flushTime = flushTimeSeconds;
        this.queue = new ArrayBlockingQueue<>(flushSize * 2);
        executorService = Executors.newSingleThreadScheduledExecutor();
        elapsedTime = new AtomicInteger(0);
        //开启定时线程
        startScheduleProcessBuffer();
    }

    public void add(T data) {
        try {
            queue.put(data);
        } catch (InterruptedException e) {
            logger.warn(" DataBuffer Interrupted: ", e);
        }
    }

    /**
     * 定期处理buffer中数据，并写入ClickHouse
     */
    public void startScheduleProcessBuffer() {
        logger.info("scheduleProcessBuffer start");
        //1s检查1次
        executorService.scheduleAtFixedRate(() -> {
            int count = queue.size();
            int time = elapsedTime.incrementAndGet();
            //flush
            if (count >= flushSize || time >= flushTime) {
                try {
                    logger.info("flush buffer size:{}, time:{}", count, time);
                    flushBuffer();
                    elapsedTime.set(0);
                } catch (Exception e) {
                    logger.warn("scheduleProcessBuffer error:", e);
                }
            }
        }, 1000, 1000, TimeUnit.MILLISECONDS);
    }

    /**
     * 将buffer数据写入ClickHouse
     */
    private void flushBuffer() {
        List<T> buffer = new ArrayList<>();
        queue.drainTo(buffer, queue.size());

        if (CollUtil.isNotEmpty(buffer)) {
            //写入ClickHouse
            flushTask.run(buffer);
        }
        elapsedTime.set(0);
    }

    public void flushAll(){
        logger.info("flushAll start");
        flushBuffer();
    }


}
