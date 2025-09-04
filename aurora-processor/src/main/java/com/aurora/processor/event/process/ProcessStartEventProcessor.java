package com.aurora.processor.event.process;

import com.aurora.grpc.EventDataMessage;
import com.aurora.processor.event.AbstractEventProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 进程启动事件
 * @author yangjunwei
 * @date 2025/8/22 15:47
 */
public class ProcessStartEventProcessor extends AbstractEventProcessor {

    private static final Logger logger = LogManager.getLogger(ProcessStartEventProcessor.class);

    @Override
    public void processEvent(EventDataMessage event) {

    }




}
