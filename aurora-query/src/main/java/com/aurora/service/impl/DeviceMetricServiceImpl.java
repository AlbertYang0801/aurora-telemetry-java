package com.aurora.service.impl;

import com.aurora.entity.DeviceMetricDo;
import com.aurora.mapper.DeviceMetricMapper;
import com.aurora.service.DeviceMetricService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangjunwei
 * @date 2025/7/16 16:16
 */
@Service
public class DeviceMetricServiceImpl extends ServiceImpl<DeviceMetricMapper, DeviceMetricDo> implements DeviceMetricService {

    @Override
    public List<DeviceMetricDo> testSelect() {
        return getBaseMapper().testSelect();
    }


}
