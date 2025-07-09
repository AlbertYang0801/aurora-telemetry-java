package com.aurora.processor;

import cn.hutool.core.thread.NamedThreadFactory;
import com.aurora.clickhouse.ClickHouseDataBuffer;
import com.aurora.clickhouse.DataBuffer;
import com.aurora.config.KafkaCustomProperties;
import com.aurora.entity.BaseMetric;
import com.aurora.service.MetricService;
import com.aurora.handler.BlockReceiverRejectionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangjunwei
 * @date 2025/7/7 18:43
 */
@Component
public class MetricDataProcessor extends DataProcessor {

    private static final Logger logger = LogManager.getLogger(MetricDataProcessor.class);

    @Resource
    KafkaCustomProperties kafkaCustomProperties;
    @Resource
    KafkaProperties kafkaProperties;

    private static ThreadPoolExecutor executor;

    private DataBuffer<BaseMetric> dataBuffer;

    protected void initializeThreadPool() {
        try {
            executor = new ThreadPoolExecutor(kafkaCustomProperties.getMetricThreadPoolSize(),
                    kafkaCustomProperties.getMetricThreadPoolSize(),
                    0, TimeUnit.MILLISECONDS,
                    //队列容量为单次最大拉取消息数量
                    new LinkedBlockingQueue<>(kafkaProperties.getConsumer().getMaxPollRecords()),
                    new NamedThreadFactory("metric-data-processor", true),
                    new BlockReceiverRejectionHandler(500, "metric-data-processor"));
        } catch (Throwable ex) {
            logger.error("metric-data-processor initialized error! ", ex);
        }
    }

    @Override
    public void process(byte[] data) {
        if (executor == null) {
            initializeThreadPool();
        }
        executor.execute(new MetricService(data));
    }


    @Override
    public int remainingCapacity() {
        if (executor == null) {
            initializeThreadPool();
        }
        return executor.getQueue().remainingCapacity();
    }


}
