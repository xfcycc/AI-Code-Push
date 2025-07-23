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
 * AI模型信息表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "code_push.sys_ai_model_info")
public class SysAiModelInfo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 模型名称
     */
    @TableField(value = "model_name")
    private String modelName;

    /**
     * 模型类型
     */
    @TableField(value = "model_type")
    private String modelType;

    /**
     * 提供商
     */
    @TableField(value = "provider")
    private String provider;

    /**
     * 版本
     */
    @TableField(value = "version")
    private String version;

    /**
     * 基础URL
     */
    @TableField(value = "base_url")
    private String baseUrl;

    /**
     * 是否需要API密钥
     */
    @TableField(value = "api_key_required")
    private Boolean apiKeyRequired;

    /**
     * 最大令牌数
     */
    @TableField(value = "max_tokens")
    private Integer maxTokens;

    /**
     * 速率限制
     */
    @TableField(value = "rate_limit")
    private Integer rateLimit;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    private String status;

    /**
     * 元数据JSON
     */
    @TableField(value = "metadata")
    private String metadata;

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