package com.aurora.clickhouse;


import com.aurora.entity.BaseClickhouseData;
import com.aurora.entity.MetricDo;
import com.aurora.enums.ClickHouseDataType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ck数据buffer
 *
 * @author AlbertYang
 */
@Component
public class ClickHouseDataBuffer {

    private final Map<ClickHouseDataType, DataBuffer<BaseClickhouseData>> bufferMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        Arrays.stream(ClickHouseDataType.values()).forEach(type -> {
            bufferMap.put(type, new DataBuffer<>(new FlushTask<>(type), type.getFlushSize(), type.getFlushTimeSeconds()));
            //新增其它类型的buffer

        });
    }

    public void insertData(BaseClickhouseData metric, ClickHouseDataType type) {
        //新增数据到buffer
        this.getDataBuffer(type).add(metric);
    }

    private DataBuffer<BaseClickhouseData> getDataBuffer(ClickHouseDataType clickHouseDataType) {
        if (!bufferMap.containsKey(clickHouseDataType)) {
            throw new RuntimeException("Failed to get buffer for type " + clickHouseDataType.getTableName());
        }
        return bufferMap.get(clickHouseDataType);
    }

    @PreDestroy
    public void shutdown() {
        bufferMap.values().forEach(DataBuffer::flushAll);
    }


}
