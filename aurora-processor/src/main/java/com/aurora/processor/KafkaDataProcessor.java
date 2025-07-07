package com.aurora.processor;

import cn.hutool.core.thread.NamedThreadFactory;
import com.aurora.config.KafkaCustomConfig;
import com.aurora.service.MetricService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangjunwei
 * @date 2025/7/7 18:43
 */
@Component
public class KafkaDataProcessor extends AbstractThreadPool implements DataProcessor {

    private static final Logger logger = LogManager.getLogger(KafkaDataProcessor.class);

    @Autowired
    KafkaCustomConfig kafkaCustomConfig;

    public KafkaDataProcessor() {
        init();
    }

    @Override
    protected ThreadPoolExecutor initializeThreadPool() {
        try {
            return new ThreadPoolExecutor(kafkaCustomConfig.getThreadPoolSize(),
                    kafkaCustomConfig.getThreadPoolSize(),
                    0, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(kafkaCustomConfig.getThreadPoolSize()),
                    new NamedThreadFactory("kafka-data-processor", true),
                    new BlockReceiverRejectionHandler(500, "kafka-data-processor"));
        } catch (Throwable ex) {
            logger.error("kafka-data-processor initialized error! ", ex);
        }
        return null;
    }

    @Override
    public void process(byte[] data) {
        executeTask(new MetricService(data));
    }




}
