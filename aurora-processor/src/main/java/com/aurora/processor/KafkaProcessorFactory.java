package com.aurora.processor;

import cn.hutool.core.util.StrUtil;
import com.aurora.config.KafkaCustomProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yangjunwei
 * @date 2025/7/8 11:05
 */
@Component
public class KafkaProcessorFactory {

    @Resource
    private KafkaCustomProperties kafkaCustomProperties;
    @Resource
    private MetricDataProcessor metricDataProcessor;

    public DataProcessor getProcessor(String topic) {
        if (StrUtil.equals(topic, kafkaCustomProperties.getMetricTopic())) {
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
