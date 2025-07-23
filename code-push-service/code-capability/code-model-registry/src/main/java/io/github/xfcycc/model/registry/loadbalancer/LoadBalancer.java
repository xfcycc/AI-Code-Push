package io.github.xfcycc.model.registry.loadbalancer;

import io.github.xfcycc.model.registry.entity.ServiceInstance;

import java.util.List;

/**
 * 负载均衡器接口
 */
public interface LoadBalancer {
    
    /**
     * 从实例列表中选择一个实例
     * @param instances 可用实例列表
     * @param serviceName 服务名称
     * @return 选中的实例
     */
    ServiceInstance choose(List<ServiceInstance> instances, String serviceName);
    
    /**
     * 获取负载均衡策略名称
     * @return 策略名称
     */
    String getName();
}