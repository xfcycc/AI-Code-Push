package io.github.xfcycc.model.registry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.xfcycc.model.registry.entity.SysAiModelServiceConfig;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @author caiguoyu
 * @date 2025/7/23
 */
public interface SysAiModelServiceConfigMapper extends BaseMapper<SysAiModelServiceConfig> {
    int updateBatch(@Param("list") List<SysAiModelServiceConfig> list);

    int batchInsert(@Param("list") List<SysAiModelServiceConfig> list);
}