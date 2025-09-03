package com.aurora.processor.custom;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.aurora.clickhouse.ClickHouseDataExporter;
import com.aurora.entity.DeviceMetricDo;
import com.aurora.clickhouse.ClickHouseDataFlushType;
import com.aurora.processor.DataProcessor;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author AlbertYang
 * @date 2025/7/7 18:43
 */
@Component
public class DeviceMetricDataProcessor extends DataProcessor {

    private static final Logger logger = LogManager.getLogger(DeviceMetricDataProcessor.class);

    @Override
    public void process(byte[] data) {
        //异步处理消费的数据
        executor.execute(() -> {
            //protobuf 转 JavaBean
            DeviceMetricMessage deviceMetricMessage = null;
            try {
                deviceMetricMessage = DeviceMetricMessage.parseFrom(data);
            } catch (InvalidProtocolBufferException e) {
                throw new RuntimeException(e);
            }
            List<DeviceMetricDo> metricMessageVos = convert(deviceMetricMessage);
            if(CollUtil.isEmpty(metricMessageVos)){
                logger.warn("DeviceMetricDataProcessor 接收数据为空");
                return;
            }

            //数据处理

            //指标丰富

            //指标告警

            SpringUtil.getBean(ClickHouseDataExporter.class).exportBatch(metricMessageVos, ClickHouseDataFlushType.DEVICE_METRIC);
        });
    }

    private List<DeviceMetricDo> convert(DeviceMetricMessage deviceMetricMessage) {
        if (Objects.isNull(deviceMetricMessage) || CollUtil.isEmpty(deviceMetricMessage.getMetricsList())) {
            return new ArrayList<>();
        }
        List<DeviceMetricDo> deviceMetricDos = new ArrayList<>();
        for (DeviceMetricItem deviceMetricItem : deviceMetricMessage.getMetricsList()) {
            DeviceMetricDo deviceMetricDo = new DeviceMetricDo();
            deviceMetricDo.setPlaceId(deviceMetricMessage.getPlaceId());
            deviceMetricDo.setIp(deviceMetricMessage.getIp());
            deviceMetricDo.setTime(convertTime(deviceMetricMessage.getTime()));
            deviceMetricDo.setTid(deviceMetricItem.getTid());
            deviceMetricDo.setValue(deviceMetricItem.getValue());
            deviceMetricDos.add(deviceMetricDo);
        }
        return deviceMetricDos;
    }


}





