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
 * AI服务实例表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "code_push.sys_ai_model_service_instance")
public class SysAiModelServiceInstance implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 服务名称
     */
    @TableField(value = "service_name")
    private String serviceName;

    /**
     * 实例ID
     */
    @TableField(value = "instance_id")
    private String instanceId;

    /**
     * IP地址
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * 端口
     */
    @TableField(value = "port")
    private Integer port;

    /**
     * 权重
     */
    @TableField(value = "weight")
    private Double weight;

    /**
     * 健康状态
     */
    @TableField(value = "healthy")
    private Boolean healthy;

    /**
     * 是否启用
     */
    @TableField(value = "enabled")
    private Boolean enabled;

    /**
     * 元数据JSON
     */
    @TableField(value = "metadata")
    private String metadata;

    /**
     * 最后心跳时间
     */
    @TableField(value = "last_beat")
    private LocalDateTime lastBeat;

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