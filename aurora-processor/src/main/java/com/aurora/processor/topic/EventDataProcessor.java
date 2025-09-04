package com.aurora.processor.topic;

import com.aurora.grpc.EventDataMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * @author yangjunwei
 * @date 2025/7/14 17:42
 */
public class EventDataProcessor extends TopicDataProcessor {

    @Override
    public void process(ConsumerRecord<String, byte[]> record) {
        //事件分发
        EventDataMessage eventDataMessage = parseProto(record.value());

        //event支持二级分发，简化事件上报逻辑


    }

    private EventDataMessage parseProto(byte[] value) {
        try {
            return EventDataMessage.parseFrom(value);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }


}
