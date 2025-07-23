package io.github.xfcycc.model.registry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.xfcycc.model.registry.entity.SysAiModelInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @author caiguoyu
 * @date 2025/7/23
 */
public interface SysAiModelInfoMapper extends BaseMapper<SysAiModelInfo> {
    int updateBatch(@Param("list") List<SysAiModelInfo> list);

    int batchInsert(@Param("list") List<SysAiModelInfo> list);
}