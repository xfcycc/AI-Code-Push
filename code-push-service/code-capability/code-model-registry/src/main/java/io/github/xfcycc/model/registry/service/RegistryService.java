package io.github.xfcycc.model.registry.service;

import io.github.xfcycc.model.registry.entity.ServiceInstance;

import java.util.List;

/**
 * AI注册中心核心服务接口
 */
public interface RegistryService {
    
    /**
     * 注册服务实例
     * @param instance 服务实例
     * @return 是否注册成功
     */
    boolean registerInstance(ServiceInstance instance);
    
    /**
     * 注销服务实例
     * @param serviceName 服务名称
     * @param instanceId 实例ID
     * @return 是否注销成功
     */
    boolean deregisterInstance(String serviceName, String instanceId);
    
    /**
     * 发送心跳
     * @param serviceName 服务名称
     * @param instanceId 实例ID
     * @return 是否成功
     */
    boolean sendHeartbeat(String serviceName, String instanceId);
    
    /**
     * 获取服务的所有健康实例
     * @param serviceName 服务名称
     * @return 健康实例列表
     */
    List<ServiceInstance> getHealthyInstances(String serviceName);
    
    /**
     * 获取服务的所有实例
     * @param serviceName 服务名称
     * @return 所有实例列表
     */
    List<ServiceInstance> getAllInstances(String serviceName);
    
    /**
     * 获取所有服务名称
     * @return 服务名称列表
     */
    List<String> getAllServiceNames();
    
    /**
     * 更新实例健康状态
     * @param serviceName 服务名称
     * @param instanceId 实例ID
     * @param healthy 健康状态
     * @return 是否更新成功
     */
    boolean updateInstanceHealth(String serviceName, String instanceId, boolean healthy);
    
    /**
     * 启用/禁用实例
     * @param serviceName 服务名称
     * @param instanceId 实例ID
     * @param enabled 是否启用
     * @return 是否更新成功
     */
    boolean updateInstanceEnabled(String serviceName, String instanceId, boolean enabled);
}