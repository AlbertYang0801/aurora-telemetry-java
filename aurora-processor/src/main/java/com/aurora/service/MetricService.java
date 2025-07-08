package com.aurora.service;

import cn.hutool.json.JSONUtil;
import com.aurora.grpc.MetricItem;
import com.aurora.grpc.MetricMessage;
import com.aurora.runner.KafkaReceiverRunner;
import com.aurora.vo.MetricMessageVo;
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
        List<MetricMessageVo> metricMessageVos = convert(metricMessage);

        //TODO 本地聚合

        //TODO 上传到ck
        System.out.println(JSONUtil.toJsonStr(metricMessageVos));
    }

    private List<MetricMessageVo> convert(MetricMessage metricMessage) {
        List<MetricMessageVo> metricMessageVos = new ArrayList<>();
        for (MetricItem metricItem : metricMessage.getMetricsList()) {
            MetricMessageVo metricMessageVo = new MetricMessageVo();
            metricMessageVo.setPlaceId(metricMessage.getPlaceId());
            metricMessageVo.setIp(metricMessage.getIp());
            metricMessageVo.setTime(metricMessage.getTime());
            metricMessageVo.setPid(metricItem.getPid());
            metricMessageVo.setTid(metricItem.getTid());
            metricMessageVo.setValue(metricItem.getValue());
            metricMessageVos.add(metricMessageVo);
        }
        return metricMessageVos;
    }


}
