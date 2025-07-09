package com.aurora.clickhouse;

import cn.hutool.core.collection.CollUtil;
import com.clickhouse.client.api.Client;
import com.clickhouse.client.api.insert.InsertResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author yangjunwei
 * @date 2025/7/9 15:41
 */
@Component
public class ClickHouseInsertHandler {

    private static final Logger logger = LogManager.getLogger(ClickHouseInsertHandler.class);

    @Resource
    Client clickHouseClient;

    public <T> long batchInsert(String table, List<T> events) {
        if (CollectionUtils.isEmpty(events)) {
            return 0;
        }
        InsertResponse response = null;
        try {
            // 1. 执行批量插入
            response = clickHouseClient.insert(table, events).get();

            // 2. 处理响应
            long writtenRows = response.getWrittenRows();
            if (writtenRows != events.size()) {
                logger.warn("Table: {} - Partial write: expected={}, actual={}",
                        table, events.size(), writtenRows);
            } else {
                logger.debug("Table: {} - Successfully wrote {} rows (batch size: {})",
                        table, writtenRows, events.size());
            }
            return writtenRows;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Table: {} - Insert interrupted, batch size: {}", table, events.size(), e);
            return 0;
        } catch (ExecutionException e) {
            logger.error("Table: {} - Insert failed, batch size: {}", table, events.size(), e);
            return 0;
        } finally {
            if (response != null) {
                // 应尽快关闭此对象以释放连接，因为在读取完之前响应的所有数据之前，连接不能被重用。
                response.close();
            }
        }
    }

    /**
     * 批量写入数据到ClickHouse
     *
     * @return 成功写入的行数
     */
    public <T> long batchInsert(String table, List<T> events, int batchSize) {
        if (CollectionUtils.isEmpty(events)) {
            return 0;
        }
        return CollUtil.split(events, batchSize).parallelStream()
                .mapToLong(batch -> batchInsert(table, batch)).sum();
    }


}
