package io.github.xfcycc.model.registry.client;

import io.github.xfcycc.model.registry.entity.ServiceInstance;
import io.github.xfcycc.model.registry.loadbalancer.LoadBalancer;
import io.github.xfcycc.model.registry.service.RegistryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * AI服务发现客户端
 */
@Component
public class DiscoveryClient {
    
    private static final Logger logger = LoggerFactory.getLogger(DiscoveryClient.class);
    
    @Autowired
    private RegistryService registryService;
    
    @Autowired
    @Qualifier("roundRobinLoadBalancer")
    private LoadBalancer defaultLoadBalancer;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    private static final String CACHE_KEY_PREFIX = "ai:registry:cache:";
    private static final long CACHE_TTL = 30; // 缓存30秒
    
    /**
     * 获取服务实例（带负载均衡）
     */
    public ServiceInstance getInstance(String serviceName) {
        return getInstance(serviceName, defaultLoadBalancer);
    }
    
    /**
     * 获取服务实例（指定负载均衡策略）
     */
    public ServiceInstance getInstance(String serviceName, LoadBalancer loadBalancer) {
        List<ServiceInstance> instances = getHealthyInstances(serviceName);
        if (instances.isEmpty()) {
            logger.warn("No healthy instances found for service: {}", serviceName);
            return null;
        }
        
        return loadBalancer.choose(instances, serviceName);
    }
    
    /**
     * 获取健康的服务实例列表（带缓存）
     */
    @SuppressWarnings("unchecked")
    public List<ServiceInstance> getHealthyInstances(String serviceName) {
        String cacheKey = CACHE_KEY_PREFIX + serviceName;
        
        try {
            // 先从缓存获取
            List<ServiceInstance> cachedInstances = (List<ServiceInstance>) redisTemplate.opsForValue().get(cacheKey);
            if (cachedInstances != null) {
                return cachedInstances;
            }
        } catch (Exception e) {
            logger.warn("Failed to get instances from cache for service: {}", serviceName, e);
        }
        
        // 从注册中心获取
        List<ServiceInstance> instances = registryService.getHealthyInstances(serviceName);
        
        try {
            // 更新缓存
            redisTemplate.opsForValue().set(cacheKey, instances, CACHE_TTL, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.warn("Failed to cache instances for service: {}", serviceName, e);
        }
        
        return instances;
    }
    
    /**
     * 调用AI服务
     */
    public <T> T callService(String serviceName, String path, Object request, Class<T> responseType) {
        ServiceInstance instance = getInstance(serviceName);
        if (instance == null) {
            throw new RuntimeException("No available instance for service: " + serviceName);
        }
        
        String url = "http://" + instance.getAddress() + path;
        
        // 这里可以集成HTTP客户端进行实际调用
        // 例如使用RestTemplate、WebClient或Feign
        logger.info("Calling service: {} at {}", serviceName, url);
        
        // TODO: 实现具体的HTTP调用逻辑
        return null;
    }
    
    /**
     * 清除服务缓存
     */
    public void clearCache(String serviceName) {
        String cacheKey = CACHE_KEY_PREFIX + serviceName;
        redisTemplate.delete(cacheKey);
    }
    
    /**
     * 获取所有服务名称
     */
    public List<String> getServices() {
        return registryService.getAllServiceNames();
    }
}