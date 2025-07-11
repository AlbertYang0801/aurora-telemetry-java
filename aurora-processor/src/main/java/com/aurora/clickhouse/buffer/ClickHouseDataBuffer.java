package com.aurora.clickhouse.buffer;


import cn.hutool.json.JSONUtil;
import com.aurora.clickhouse.ClickHouseDataFlushType;
import com.aurora.entity.BaseClickhouseData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ck数据buffer
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
            //新增其它类型的buffer

        });
        logger.info("init buffer success {}", JSONUtil.toJsonStr(ClickHouseDataFlushType.getAllTableNames()));
    }

    public void insertData(BaseClickhouseData metric, ClickHouseDataFlushType type) {
        //新增数据到buffer
        this.getDataBuffer(type).add(metric);
    }

    private DataBuffer<BaseClickhouseData> getDataBuffer(ClickHouseDataFlushType clickHouseDataFlushType) {
        if (!bufferMap.containsKey(clickHouseDataFlushType)) {
            throw new RuntimeException("Failed to get buffer for type " + clickHouseDataFlushType.getTableName());
        }
        return bufferMap.get(clickHouseDataFlushType);
    }

    @PreDestroy
    public void shutdown() {
        bufferMap.values().forEach(DataBuffer::flushAll);
    }


}
