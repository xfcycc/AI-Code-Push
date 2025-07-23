package io.github.xfcycc.model.registry.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * AI服务实例实体
 */
public class ServiceInstance {
    private Long id;
    private String serviceName;
    private String instanceId;
    private String ip;
    private Integer port;
    private Double weight = 1.0;
    private Boolean healthy = true;
    private Boolean enabled = true;
    private Boolean ephemeral = true;
    private String clusterName = "DEFAULT";
    private String metadata;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastBeat;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // 构造函数
    public ServiceInstance() {
    }

    public ServiceInstance(String serviceName, String instanceId, String ip, Integer port) {
        this.serviceName = serviceName;
        this.instanceId = instanceId;
        this.ip = ip;
        this.port = port;
        this.lastBeat = LocalDateTime.now();
    }

    // 元数据操作方法
    public Map<String, String> getMetadataMap() {
        if (metadata == null || metadata.isEmpty()) {
            return new HashMap<>();
        }
        try {
            return objectMapper.readValue(metadata, new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    public void setMetadataMap(Map<String, String> metadataMap) {
        try {
            this.metadata = objectMapper.writeValueAsString(metadataMap);
        } catch (Exception e) {
            this.metadata = "{}";
        }
    }

    // 获取服务地址
    public String getAddress() {
        return ip + ":" + port;
    }

    // 检查是否健康且可用
    public boolean isAvailable() {
        return Boolean.TRUE.equals(healthy) && Boolean.TRUE.equals(enabled);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Boolean getHealthy() {
        return healthy;
    }

    public void setHealthy(Boolean healthy) {
        this.healthy = healthy;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getEphemeral() {
        return ephemeral;
    }

    public void setEphemeral(Boolean ephemeral) {
        this.ephemeral = ephemeral;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public LocalDateTime getLastBeat() {
        return lastBeat;
    }

    public void setLastBeat(LocalDateTime lastBeat) {
        this.lastBeat = lastBeat;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}