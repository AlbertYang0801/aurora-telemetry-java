package com.aurora.clickhouse.buffer;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.aurora.clickhouse.ClickHouseDataFlushType;
import com.aurora.clickhouse.FlushTask;
import com.aurora.entity.BaseClickhouseData;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClickHouse数据buffer
 *
 * @author AlbertYang
 */
@Component
public class ClickHouseDataBuffer {

    private static final Logger logger = LogManager.getLogger(ClickHouseDataBuffer.class);

    private final Map<ClickHouseDataFlushType, DataBuffer<BaseClickhouseData>> bufferMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        Arrays.stream(ClickHouseDataFlushType.values()).forEach(type -> {
            bufferMap.put(type, new DataBuffer<>(type));
        });
        logger.info("init buffer success {}", JSONUtil.toJsonStr(ClickHouseDataFlushType.getAllTableNames()));
    }

    public void insertData(BaseClickhouseData clickhouseData, ClickHouseDataFlushType type) {
        if (Objects.isNull(clickhouseData)) {
            return;
        }
        //新增数据到buffer
        this.getDataBuffer(type).add(clickhouseData);
    }

    public void insertDataNow(List<? extends BaseClickhouseData> data, ClickHouseDataFlushType type) {
        if (CollUtil.isEmpty(data)) {
            return;
        }
        //直接新增数据到clickhouse
        FlushTask flushTask = this.getDataBuffer(type).getFlushTask();
        flushTask.run(data);
    }

    public void flushNow(ClickHouseDataFlushType clickHouseDataFlushType) {
        DataBuffer<BaseClickhouseData> dataBuffer = this.getDataBuffer(clickHouseDataFlushType);
        dataBuffer.flushAll();
    }

    private DataBuffer<BaseClickhouseData> getDataBuffer(ClickHouseDataFlushType clickHouseDataFlushType) {
        if (!bufferMap.containsKey(clickHouseDataFlushType)) {
            throw new RuntimeException("Failed to get buffer for type " + clickHouseDataFlushType.getTableName());
        }
        return bufferMap.get(clickHouseDataFlushType);
    }

    @PreDestroy
    public void shutdown() {
        logger.info("ClickHouseDataBuffer shutdown");
        bufferMap.values().forEach(DataBuffer::flushAll);
    }


}
