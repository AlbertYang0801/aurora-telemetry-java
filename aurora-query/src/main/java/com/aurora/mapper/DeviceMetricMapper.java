package com.aurora.mapper;

import com.aurora.entity.DeviceMetricDo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author AlbertYang
 * @date 2025/7/16 16:08
 */
@Mapper
public interface DeviceMetricMapper extends BaseMapper<DeviceMetricDo> {

    List<DeviceMetricDo> testSelect();

}
