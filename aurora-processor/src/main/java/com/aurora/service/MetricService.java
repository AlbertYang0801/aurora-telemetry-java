package com.aurora.service;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.aurora.clickhouse.ClickHouseDataBuffer;
import com.aurora.clickhouse.ClickHouseDataExporter;
import com.aurora.clickhouse.ClickHouseInsertHandler;
import com.aurora.entity.MetricDo;
import com.aurora.enums.ClickHouseDataType;
import com.aurora.grpc.MetricItem;
import com.aurora.grpc.MetricMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangjunwei
 * @date 2025/7/7 19:08
 */
public class MetricService implements Runnable {

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
