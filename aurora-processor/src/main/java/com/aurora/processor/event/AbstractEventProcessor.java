package com.aurora.processor.event;

import cn.hutool.extra.spring.SpringUtil;
import com.aurora.clickhouse.ClickHouseDataExporter;
import com.aurora.grpc.EventDataMessage;

/**
 * @author AlbertYang
 * @date 2025/8/22 16:02
 */
public abstract class AbstractEventProcessor {

    abstract public void processEvent(EventDataMessage event);

    protected ClickHouseDataExporter dataExporter() {
        return SpringUtil.getBean(ClickHouseDataExporter.class);
    }

}
