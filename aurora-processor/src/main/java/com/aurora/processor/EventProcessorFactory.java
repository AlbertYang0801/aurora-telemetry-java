package com.aurora.processor;


import com.aurora.processor.event.AbstractEventProcessor;
import com.aurora.processor.event.DefaultEventProcessor;
import com.aurora.processor.event.process.ProcessStartEventProcessor;
import com.aurora.processor.event.process.ProcessStopEventProcessor;

/**
 * 按topic分发
 *
 * @author AlbertYang
 * @date 2025/7/8 11:05
 */
public enum EventProcessorFactory {

    //------------------------------------进程事件---------------------------------

    DEFAULT(1000, new DefaultEventProcessor()),


    /**
     * 进程启动
     */
    PROCESS_START(1011, new ProcessStartEventProcessor()),
    /**
     * 进程停止
     */
    PROCESS_SHUTDOWN(1012, new ProcessStopEventProcessor());


    /**
     * 事件类型
     */
    private final Integer eventType;

    private final AbstractEventProcessor eventProcessor;

    EventProcessorFactory(Integer eventType, AbstractEventProcessor eventProcessor) {
        this.eventType = eventType;
        this.eventProcessor = eventProcessor;
    }

    public static AbstractEventProcessor getProcessor(Integer eventType) {
        for (EventProcessorFactory factory : values()) {
            if (factory.eventType.equals(eventType)) {
                return factory.eventProcessor;
            }
        }
        //当作默认事件处理
        return DEFAULT.eventProcessor;
    }


}
