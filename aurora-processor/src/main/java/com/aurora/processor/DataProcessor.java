package com.aurora.processor;

/**
 * @author yangjunwei
 * @date 2025/7/7 18:43
 */
public abstract class DataProcessor {

    /**
     * 处理数据
     *
     * @param data
     */
    abstract public void process(byte[] data);

    /**
     * 剩余可提交任务数量
     *
     * @return
     */
    abstract public int remainingCapacity();


}
