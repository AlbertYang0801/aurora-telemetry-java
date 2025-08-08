package com.aurora.processor;

import cn.hutool.core.util.StrUtil;
import com.aurora.config.KafkaCustomProperties;
import com.aurora.processor.custom.DeviceMetricDataProcessor;
import com.aurora.processor.custom.ProcessMetricDataProcessor;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;


/**
 * @author AlbertYang
 * @date 2025/7/8 11:05
 */
@Component
public class KafkaProcessorFactory {

    @Resource
    private KafkaCustomProperties kafkaCustomProperties;
    @Resource
    private DeviceMetricDataProcessor deviceMetricDataProcessor;
    @Resource
    private ProcessMetricDataProcessor processMetricDataProcessor;

    public DataProcessor getProcessor(String topic) {
        if (StrUtil.equals(topic, kafkaCustomProperties.getDeviceMetricTopic())) {
            return deviceMetricDataProcessor;
        }
        if(StrUtil.equals(topic, kafkaCustomProperties.getProcessMetricTopic())){
            return processMetricDataProcessor;
        }
        return null;
    }



}
