package com.aurora.processor;

import com.aurora.processor.topic.EventDataProcessor;
import com.aurora.processor.topic.MetricDataProcessor;
import com.aurora.processor.topic.TopicDataProcessor;

/**
 * 按topic分发
 *
 * @author AlbertYang
 * @date 2025/7/8 11:05
 */
public enum TopicDataProcessorFactory {

    EVENT("event", new EventDataProcessor()),
    METRIC("metric", new MetricDataProcessor());

    /**
     * 消费者名称
     */
    private final String topic;
    /**
     * 消费者数据处理器
     */
    private final TopicDataProcessor processor;

    TopicDataProcessorFactory(String topic, TopicDataProcessor processor) {
        this.topic = topic;
        this.processor = processor;
    }

    public static boolean contains(String topic) {
        for (TopicDataProcessorFactory factory : values()) {
            if (factory.topic.equals(topic)) {
                return true;
            }
        }
        return false;
    }

    public static TopicDataProcessor getProcessor(String topic) {
        for (TopicDataProcessorFactory factory : values()) {
            if (factory.topic.equals(topic)) {
                return factory.processor;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TopicDataProcessor processor = getProcessor("metric");
        TopicDataProcessor processor1 = getProcessor("metric");
        System.out.println(processor);
        System.out.println(processor1);

        TopicDataProcessor processor2 = getProcessor("event");
        TopicDataProcessor processor12 = getProcessor("event");
        System.out.println(processor2);
        System.out.println(processor12);
    }


}
