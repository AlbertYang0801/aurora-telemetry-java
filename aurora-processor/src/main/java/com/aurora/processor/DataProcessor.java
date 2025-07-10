package com.aurora.processor;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.NamedThreadFactory;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.aurora.config.KafkaCustomProperties;
import com.aurora.handler.BlockReceiverRejectionHandler;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangjunwei
 * @date 2025/7/7 18:43
 */
public abstract class DataProcessor {

    // 静态公共线程池
    protected static ThreadPoolExecutor executor;

    // 静态初始化块配置线程池
    static {
        KafkaCustomProperties kafkaCustomProperties = SpringUtil.getBean(KafkaCustomProperties.class);
        KafkaProperties kafkaProperties = SpringUtil.getBean(KafkaProperties.class);
        executor = new ThreadPoolExecutor(
                kafkaCustomProperties.getConsumerThreadPoolSize(),
                kafkaCustomProperties.getConsumerThreadPoolSize(),
                0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(kafkaProperties.getConsumer().getMaxPollRecords()),
                new NamedThreadFactory("metric-data-processor", true),
                new BlockReceiverRejectionHandler(500, "metric-data-processor")
        );
    }

    public void processData(byte[] data) {
        executor.submit(() -> process(data));
    }

    /**
     * 处理数据
     *
     * @param data
     */
    abstract protected void process(byte[] data);


    /**
     * 剩余可提交任务数量
     *
     * @return
     */
    public static int remainingCapacity() {
        return executor.getQueue().remainingCapacity();
    }


}
