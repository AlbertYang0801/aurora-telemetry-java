package com.aurora.processor;

import cn.hutool.core.thread.NamedThreadFactory;
import cn.hutool.extra.spring.SpringUtil;
import com.aurora.clickhouse.ClickHouseDataExporter;
import com.aurora.config.KafkaCustomProperties;
import com.aurora.entity.MetricDo;
import com.aurora.enums.ClickHouseDataType;
import com.aurora.grpc.MetricItem;
import com.aurora.grpc.MetricMessage;
import com.aurora.handler.BlockReceiverRejectionHandler;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangjunwei
 * @date 2025/7/7 18:43
 */
@Component
public class MetricDataProcessor extends DataProcessor {

    private static final Logger logger = LogManager.getLogger(MetricDataProcessor.class);

    @Resource
    KafkaCustomProperties kafkaCustomProperties;
    @Resource
    KafkaProperties kafkaProperties;

    private static ThreadPoolExecutor executor;


    @Override
    public void process(byte[] data) {
        if (executor == null) {
            initializeThreadPool();
        }
        executor.execute(new MetricService(data));
    }



}


class MetricService implements Runnable {

    private static final Logger logger = LogManager.getLogger(MetricService.class);

    private final byte[] data;

    public MetricService(byte[] data) {
        this.data = data;
    }

    @Override
    public void run() {
        //protobuf 转 JavaBean
        MetricMessage metricMessage = null;
        try {
            metricMessage = MetricMessage.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
        List<MetricDo> metricMessageVos = convert(metricMessage);
        SpringUtil.getBean(ClickHouseDataExporter.class).exportBatch(metricMessageVos, ClickHouseDataType.METRIC);
    }

    private List<MetricDo> convert(MetricMessage metricMessage) {
        List<MetricDo> metricDos = new ArrayList<>();
        for (MetricItem metricItem : metricMessage.getMetricsList()) {
            MetricDo metricDo = new MetricDo();
            metricDo.setPlaceId(metricMessage.getPlaceId());
            metricDo.setIp(metricMessage.getIp());
            metricDo.setTime(metricMessage.getTime());
            metricDo.setPid(metricMessage.getPid());
            metricDo.setTid(metricItem.getTid());
            metricDo.setValue(metricItem.getValue());
            metricDos.add(metricDo);
        }
        return metricDos;
    }


}
