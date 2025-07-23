package io.github.xfcycc.model.registry.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author caiguoyu
 * @date 2025/7/23 
 */
/**
 * AI服务配置表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "code_push.sys_ai_model_service_config")
public class SysAiModelServiceConfig implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 服务名称
     */
    @TableField(value = "service_name")
    private String serviceName;

    /**
     * 配置键
     */
    @TableField(value = "config_key")
    private String configKey;

    /**
     * 配置值
     */
    @TableField(value = "config_value")
    private String configValue;

    /**
     * 配置类型
     */
    @TableField(value = "config_type")
    private String configType;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 是否删除,0-未删除,1-已删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}