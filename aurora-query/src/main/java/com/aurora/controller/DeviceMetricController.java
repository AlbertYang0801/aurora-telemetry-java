package com.aurora.controller;

import com.aurora.entity.DeviceMetricDo;
import com.aurora.service.DeviceMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author AlbertYang
 * @date 2025/7/16 16:48
 */
@RestController
public class DeviceMetricController {

    @Autowired
    DeviceMetricService deviceMetricService;

    @GetMapping("/device/metric/list")
    public List<DeviceMetricDo> list(){
        return deviceMetricService.testSelect();
    }

}
