package com.aurora.processor;

import cn.hutool.core.util.StrUtil;
import com.aurora.config.KafkaCustomConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yangjunwei
 * @date 2025/7/8 11:05
 */
@Component
public class KafkaProcessorFactory {

    @Resource
    private KafkaCustomConfig kafkaCustomConfig;
    @Resource
    private MetricDataProcessor metricDataProcessor;

    public DataProcessor getProcessor(String topic) {
        if (StrUtil.equals(topic, kafkaCustomConfig.getMetricTopic())) {
            return metricDataProcessor;
        }
        return null;
    }

    /**
     * 全部任务的剩余容量
     * @return
     */
    public int allRemainingCapacity() {
        return metricDataProcessor.remainingCapacity();
    }


}
