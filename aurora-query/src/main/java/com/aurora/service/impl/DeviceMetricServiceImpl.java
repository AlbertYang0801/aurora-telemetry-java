package com.aurora.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.aurora.entity.DeviceMetricDo;
import com.aurora.mapper.DeviceMetricMapper;
import com.aurora.service.DeviceMetricService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AlbertYang
 * @date 2025/7/16 16:16
 */
@Service
public class DeviceMetricServiceImpl extends ServiceImpl<DeviceMetricMapper, DeviceMetricDo> implements DeviceMetricService {

    @Override
    public List<DeviceMetricDo> testSelect() {
        List<DeviceMetricDo> deviceMetricDos = getBaseMapper().testSelect();

        for (DeviceMetricDo deviceMetricDo : deviceMetricDos) {
            System.out.println(LocalDateTimeUtil.format(deviceMetricDo.getTime(), "yyyy-MM-dd HH:mm:ss"));
        }

        return deviceMetricDos;
    }


}
