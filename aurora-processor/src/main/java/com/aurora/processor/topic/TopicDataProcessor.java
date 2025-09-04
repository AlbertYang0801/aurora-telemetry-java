package com.aurora.processor.topic;

import cn.hutool.extra.spring.SpringUtil;
import com.aurora.clickhouse.ClickHouseDataExporter;
import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * 数据先按消费者分发，再按topic分发
 *
 * @author yangjunwei
 * @date 2025/7/7 18:43
 */
public abstract class TopicDataProcessor {

    abstract public void process(ConsumerRecord<String, byte[]> record);

    protected ClickHouseDataExporter dataExporter() {
        return SpringUtil.getBean(ClickHouseDataExporter.class);
    }


}
