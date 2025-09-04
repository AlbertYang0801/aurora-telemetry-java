package com.aurora.service;

import com.aurora.entity.DeviceMetricDo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author AlbertYang
 * @date 2025/7/16 16:15
 */
public interface DeviceMetricService extends IService<DeviceMetricDo> {

    List<DeviceMetricDo> testSelect();

}
