 package com.aurora.processor.event;

 import cn.hutool.json.JSONUtil;
 import com.aurora.clickhouse.ClickHouseDataFlushType;
 import com.aurora.entity.EventDataDo;
 import com.aurora.grpc.EventDataMessage;

/**
 * 默认事件
 * @author AlbertYang
 * @date 2025/8/22 15:47
 */
public class DefaultEventProcessor extends AbstractEventProcessor {

    @Override
    public void processEvent(EventDataMessage event) {
        EventDataDo convert = convert(event);
        dataExporter().export(convert, ClickHouseDataFlushType.EVENT);
    }

    private EventDataDo convert(EventDataMessage event) {
       EventDataDo eventDataDo = new EventDataDo();
       eventDataDo.setTimestamp(event.getTimestamp());
       eventDataDo.setEventType(event.getEventType());
       eventDataDo.setTraceId(event.getTraceId().toStringUtf8());
       eventDataDo.setSourceId(event.getSourceId());
       eventDataDo.setEventDataJson(JSONUtil.toJsonStr(event.getEventDataMap()));
       return eventDataDo;
    }




}
