-- AI服务实例表
CREATE TABLE sys_ai_model_service_instance (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    service_name VARCHAR(255) NOT NULL COMMENT '服务名称',
    instance_id VARCHAR(255) NOT NULL COMMENT '实例ID',
    ip VARCHAR(64) NOT NULL COMMENT 'IP地址',
    port INT NOT NULL COMMENT '端口',
    weight DOUBLE DEFAULT 1.0 COMMENT '权重',
    healthy BOOLEAN DEFAULT TRUE COMMENT '健康状态',
    enabled BOOLEAN DEFAULT TRUE COMMENT '是否启用',
    metadata TEXT COMMENT '元数据JSON',
    last_beat TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '最后心跳时间',
    is_deleted TINYINT DEFAULT 0 COMMENT '是否删除,0-未删除,1-已删除',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_service_instance (service_name, instance_id),
    INDEX idx_service_name (service_name)
) COMMENT='AI服务实例表';

-- AI服务配置表
CREATE TABLE sys_ai_model_service_config (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    service_name VARCHAR(255) NOT NULL COMMENT '服务名称',
    config_key VARCHAR(255) NOT NULL COMMENT '配置键',
    config_value TEXT COMMENT '配置值',
    config_type VARCHAR(50) DEFAULT 'text' COMMENT '配置类型',
    description VARCHAR(500) COMMENT '描述',
    is_deleted TINYINT DEFAULT 0 COMMENT '是否删除,0-未删除,1-已删除',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_service_config (service_name, config_key),
    INDEX idx_service_name (service_name)
) COMMENT='AI服务配置表';

-- AI模型信息表
CREATE TABLE sys_ai_model_info (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    model_name VARCHAR(255) NOT NULL COMMENT '模型名称',
    model_type VARCHAR(100) NOT NULL COMMENT '模型类型',
    provider VARCHAR(100) NOT NULL COMMENT '提供商',
    version VARCHAR(50) COMMENT '版本',
    base_url VARCHAR(500) COMMENT '基础URL',
    api_key_required BOOLEAN DEFAULT TRUE COMMENT '是否需要API密钥',
    max_tokens INT COMMENT '最大令牌数',
    rate_limit INT COMMENT '速率限制',
    status VARCHAR(20) DEFAULT 'ACTIVE' COMMENT '状态',
    metadata TEXT COMMENT '元数据JSON',
    is_deleted TINYINT DEFAULT 0 COMMENT '是否删除,0-未删除,1-已删除',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_model_name (model_name)
) COMMENT='AI模型信息表';