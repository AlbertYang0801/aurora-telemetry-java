package com.aurora.kafka;

import cn.hutool.core.convert.Convert;
import com.aurora.config.KafkaCustomConfig;
import com.aurora.grpc.DeviceMetricMessage;
import com.aurora.grpc.ProcessMetricMessage;
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

    public void sendDeviceMessage(DeviceMetricMessage deviceMetricMessage) {
        //protoBuf格式转byte数组
        byte[] data = deviceMetricMessage.toByteArray();
        //指定placeId为partitionKey
        int placeId = deviceMetricMessage.getPlaceId();
        kafkaCustomProducer.send(kafkaCustomConfig.getDeviceMetricTopic(), Convert.toStr(placeId),data);
    }

    public void sendProcessMetric(ProcessMetricMessage processMetricMessage) {
        //protoBuf格式转byte数组
        byte[] data = processMetricMessage.toByteArray();
        //指定placeId为partitionKey
        int placeId = processMetricMessage.getPlaceId();
        kafkaCustomProducer.send(kafkaCustomConfig.getProcessMetricTopic(), Convert.toStr(placeId),data);
    }



}
