package com.aurora.service;

/**
 * @author yangjunwei
 * @date 2025/7/7 19:08
 */
public class MetricService implements Runnable{

    private byte[] data;

    public MetricService(byte[] data) {
        this.data = data;
    }

    @Override
    public void run() {

    }
}
