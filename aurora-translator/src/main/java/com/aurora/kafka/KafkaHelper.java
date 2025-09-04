package com.aurora.kafka;

import com.aurora.config.KafkaCustomConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author AlbertYang
 * @date 2025/7/7 15:49
 */
@Component
public class KafkaHelper {

    @Autowired
    KafkaCustomConfig kafkaCustomConfig;
    @Autowired
    KafkaCustomProducer kafkaCustomProducer;

    /**
     * 发送事件到Kafka
     * @param eventDataMessage
     */
    public void sendEvent(EventDataMessage eventDataMessage)  {
        //protoBuf格式转byte数组
        byte[] data = eventDataMessage.toByteArray();
        //指定sourceId为partitionKey
        String sourceId = eventDataMessage.getSourceId();
        kafkaCustomProducer.send(kafkaCustomConfig.getEventTopic(), sourceId,data);
    }

    /**
     * 发送指标到Kafka
     * @param metricDataMessage
     */
    public void sendMetric(MetricDataMessage metricDataMessage)  {
        //protoBuf格式转byte数组
        byte[] data = metricDataMessage.toByteArray();
        //指定sourceId为partitionKey
        String sourceId = metricDataMessage.getSourceId();
        kafkaCustomProducer.send(kafkaCustomConfig.getMetricTopic(), sourceId,data);
    }




}
