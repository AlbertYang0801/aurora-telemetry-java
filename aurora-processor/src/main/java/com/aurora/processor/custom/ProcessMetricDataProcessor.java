package com.aurora.processor.custom;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.aurora.clickhouse.ClickHouseDataExporter;
import com.aurora.clickhouse.ClickHouseDataFlushType;
import com.aurora.entity.ProcessDeviceMetricDo;
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
public class ProcessMetricDataProcessor extends DataProcessor {

    private static final Logger logger = LogManager.getLogger(ProcessMetricDataProcessor.class);

    @Override
    public void process(byte[] data) {
        //异步处理消费的数据
        executor.execute(() -> {
            ProcessMetricMessage processMetricMessage = null;
            try {
                processMetricMessage = ProcessMetricMessage.parseFrom(data);
            } catch (InvalidProtocolBufferException e) {
                throw new RuntimeException(e);
            }
            List<ProcessDeviceMetricDo> metricMessageVos = convert(processMetricMessage);
            if(CollUtil.isEmpty(metricMessageVos)){
                logger.warn("ProcessMetricDataProcessor 接收数据为空");
                return;
            }

            //数据处理

            //指标丰富

            //指标告警

            SpringUtil.getBean(ClickHouseDataExporter.class).exportBatch(metricMessageVos, ClickHouseDataFlushType.PROCESS_METRIC);
        });
    }

    private List<ProcessDeviceMetricDo> convert(ProcessMetricMessage processMetricMessage) {
        if (Objects.isNull(processMetricMessage) || CollUtil.isEmpty(processMetricMessage.getMetricsList())) {
            return new ArrayList<>();
        }
        List<ProcessDeviceMetricDo> processDeviceMetricDos = new ArrayList<>();
        for (ProcessMetricItem processMetricItem : processMetricMessage.getMetricsList()) {
            ProcessDeviceMetricDo processDeviceMetricDo = new ProcessDeviceMetricDo();
            processDeviceMetricDo.setPlaceId(processMetricMessage.getPlaceId());
            processDeviceMetricDo.setIp(processMetricMessage.getIp());
            processDeviceMetricDo.setPid(processMetricMessage.getPid());
            processDeviceMetricDo.setTime(convertTime(processMetricMessage.getTime()));
            processDeviceMetricDo.setTid(processMetricItem.getTid());
            processDeviceMetricDo.setValue(processMetricItem.getValue());
            processDeviceMetricDos.add(processDeviceMetricDo);
        }
        return processDeviceMetricDos;
    }


}





