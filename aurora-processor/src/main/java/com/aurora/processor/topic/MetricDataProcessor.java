package com.aurora.processor.topic;

import com.aurora.clickhouse.ClickHouseDataFlushType;
import com.aurora.entity.MetricDataDo;
import com.aurora.grpc.MetricDataItem;
import com.aurora.grpc.MetricDataMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlbertYang
 * @date 2025/7/7 18:43
 */
public class MetricDataProcessor extends TopicDataProcessor {

    @Override
    public void process(ConsumerRecord<String, byte[]> record) {
        //protobuf 转 JavaBean
        MetricDataMessage metricDataMessage = parseProto(record.value());
        List<MetricDataDo> metricDataDos = convertDeviceMetric(metricDataMessage);
        dataExporter().exportBatchToBuffer(metricDataDos, ClickHouseDataFlushType.METRIC);
    }

    protected MetricDataMessage parseProto(byte[] value) {
        try {
            return MetricDataMessage.parseFrom(value);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    protected List<MetricDataDo> convertDeviceMetric(MetricDataMessage metricDataMessage) {
        List<MetricDataDo> deviceMetricDos = new ArrayList<>();
        for (MetricDataItem metricItem : metricDataMessage.getMetricsList()) {
            MetricDataDo metricDo = new MetricDataDo();
            metricDo.setTimestamp(metricDataMessage.getTimestamp());
            metricDo.setTraceId(metricDataMessage.getTraceId().toStringUtf8());
            metricDo.setSourceId(metricDataMessage.getSourceId());
            metricDo.setMetricId(metricItem.getMetricId());
            metricDo.setMetricValue(metricItem.getValue());
            deviceMetricDos.add(metricDo);
        }
        return deviceMetricDos;
    }



}





