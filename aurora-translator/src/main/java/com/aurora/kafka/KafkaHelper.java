package com.aurora.kafka;

import cn.hutool.core.convert.Convert;
import com.aurora.config.KafkaTopicConfig;
import com.aurora.grpc.MetricMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yangjunwei
 * @date 2025/7/7 15:49
 */
@Component
public class KafkaHelper {

    @Autowired
    KafkaTopicConfig kafkaTopicConfig;
    @Autowired
    KafkaCustomProducer kafkaCustomProducer;

    public void sendMetric(MetricMessage metricMessage) {
        //protoBuf格式转byte数组
        byte[] data = metricMessage.toByteArray();
        //指定placeId为partitionKey
        int placeId = metricMessage.getPlaceId();
        kafkaCustomProducer.send(kafkaTopicConfig.getMetricTopic(), Convert.toStr(placeId),data);
    }



}
