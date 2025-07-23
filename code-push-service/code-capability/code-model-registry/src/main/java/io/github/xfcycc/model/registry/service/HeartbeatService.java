package io.github.xfcycc.model.registry.service;

import io.github.xfcycc.model.registry.entity.ServiceInstance;
import io.github.xfcycc.model.registry.mapper.ServiceInstanceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 心跳检测服务
 */
@Service
public class HeartbeatService {
    
    private static final Logger logger = LoggerFactory.getLogger(HeartbeatService.class);
    
    @Autowired
    private ServiceInstanceMapper instanceMapper;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @Value("${ai.registry.heartbeat.timeout:15000}")
    private long heartbeatTimeout;
    
    private static final String HEARTBEAT_KEY_PREFIX = "ai:registry:heartbeat:";
    
    /**
     * 处理心跳
     */
    public boolean processHeartbeat(String serviceName, String instanceId) {
        try {
            // 更新数据库中的心跳时间
            int updated = instanceMapper.updateLastBeat(serviceName, instanceId, LocalDateTime.now());
            
            if (updated > 0) {
                // 更新Redis中的心跳时间戳
                String key = HEARTBEAT_KEY_PREFIX + serviceName + ":" + instanceId;
                redisTemplate.opsForValue().set(key, System.currentTimeMillis(), heartbeatTimeout, TimeUnit.MILLISECONDS);
                
                logger.debug("Heartbeat processed for service: {}, instance: {}", serviceName, instanceId);
                return true;
            }
            
            return false;
        } catch (Exception e) {
            logger.error("Failed to process heartbeat for service: {}, instance: {}", serviceName, instanceId, e);
            return false;
        }
    }
    
    /**
     * 定时检查心跳超时的实例
     */
    @Scheduled(fixedDelay = 5000) // 每5秒检查一次
    public void checkHeartbeatTimeout() {
        try {
            LocalDateTime timeoutThreshold = LocalDateTime.now().minusSeconds(heartbeatTimeout / 1000);
            
            // 查找心跳超时的实例
            List<ServiceInstance> timeoutInstances = instanceMapper.findTimeoutInstances(timeoutThreshold);
            
            for (ServiceInstance instance : timeoutInstances) {
                // 标记为不健康
                instanceMapper.updateHealthStatus(instance.getServiceName(), instance.getInstanceId(), false);
                
                // 如果是临时实例，直接删除
                if (Boolean.TRUE.equals(instance.getEphemeral())) {
                    instanceMapper.deleteInstance(instance.getServiceName(), instance.getInstanceId());
                    logger.info("Removed ephemeral instance due to heartbeat timeout: {}:{}", 
                        instance.getServiceName(), instance.getInstanceId());
                } else {
                    logger.info("Marked persistent instance as unhealthy due to heartbeat timeout: {}:{}", 
                        instance.getServiceName(), instance.getInstanceId());
                }
                
                // 清理Redis中的心跳记录
                String key = HEARTBEAT_KEY_PREFIX + instance.getServiceName() + ":" + instance.getInstanceId();
                redisTemplate.delete(key);
            }
            
        } catch (Exception e) {
            logger.error("Failed to check heartbeat timeout", e);
        }
    }
    
    /**
     * 检查实例是否在线（基于Redis心跳）
     */
    public boolean isInstanceOnline(String serviceName, String instanceId) {
        try {
            String key = HEARTBEAT_KEY_PREFIX + serviceName + ":" + instanceId;
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            logger.error("Failed to check instance online status: {}:{}", serviceName, instanceId, e);
            return false;
        }
    }
}