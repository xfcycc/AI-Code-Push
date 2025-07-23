package io.github.xfcycc.model.registry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.xfcycc.model.registry.entity.SysAiModelServiceInstance;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @author caiguoyu
 * @date 2025/7/23
 */
public interface SysAiModelServiceInstanceMapper extends BaseMapper<SysAiModelServiceInstance> {
    int updateBatch(@Param("list") List<SysAiModelServiceInstance> list);

    int batchInsert(@Param("list") List<SysAiModelServiceInstance> list);
}