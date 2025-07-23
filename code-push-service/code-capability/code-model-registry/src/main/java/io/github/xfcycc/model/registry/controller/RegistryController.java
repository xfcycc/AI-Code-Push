package io.github.xfcycc.model.registry.controller;

import io.github.xfcycc.model.registry.client.DiscoveryClient;
import io.github.xfcycc.model.registry.entity.ServiceInstance;
import io.github.xfcycc.model.registry.service.HeartbeatService;
import io.github.xfcycc.model.registry.service.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * AI注册中心REST API控制器
 */
@RestController
@RequestMapping("/api/registry")
public class RegistryController {
    
    @Autowired
    private RegistryService registryService;
    
    @Autowired
    private HeartbeatService heartbeatService;
    
    @Autowired
    private DiscoveryClient discoveryClient;
    
    /**
     * 注册服务实例
     */
    @PostMapping("/instance")
    public ResponseEntity<Map<String, Object>> registerInstance(@RequestBody ServiceInstance instance) {
        boolean success = registryService.registerInstance(instance);
        return ResponseEntity.ok(Map.of(
            "success", success,
            "message", success ? "实例注册成功" : "实例注册失败"
        ));
    }
    
    /**
     * 注销服务实例
     */
    @DeleteMapping("/instance")
    public ResponseEntity<Map<String, Object>> deregisterInstance(
            @RequestParam String serviceName,
            @RequestParam String instanceId) {
        boolean success = registryService.deregisterInstance(serviceName, instanceId);
        return ResponseEntity.ok(Map.of(
            "success", success,
            "message", success ? "实例注销成功" : "实例注销失败"
        ));
    }
    
    /**
     * 发送心跳
     */
    @PutMapping("/instance/heartbeat")
    public ResponseEntity<Map<String, Object>> sendHeartbeat(
            @RequestParam String serviceName,
            @RequestParam String instanceId) {
        boolean success = heartbeatService.processHeartbeat(serviceName, instanceId);
        return ResponseEntity.ok(Map.of(
            "success", success,
            "message", success ? "心跳发送成功" : "心跳发送失败"
        ));
    }
    
    /**
     * 获取服务的健康实例
     */
    @GetMapping("/instances/{serviceName}")
    public ResponseEntity<List<ServiceInstance>> getHealthyInstances(@PathVariable String serviceName) {
        List<ServiceInstance> instances = discoveryClient.getHealthyInstances(serviceName);
        return ResponseEntity.ok(instances);
    }
    
    /**
     * 获取服务实例（负载均衡）
     */
    @GetMapping("/instance/{serviceName}")
    public ResponseEntity<ServiceInstance> getInstance(@PathVariable String serviceName) {
        ServiceInstance instance = discoveryClient.getInstance(serviceName);
        if (instance != null) {
            return ResponseEntity.ok(instance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * 获取所有服务
     */
    @GetMapping("/services")
    public ResponseEntity<List<String>> getAllServices() {
        List<String> services = discoveryClient.getServices();
        return ResponseEntity.ok(services);
    }
    
    /**
     * 更新实例健康状态
     */
    @PutMapping("/instance/health")
    public ResponseEntity<Map<String, Object>> updateInstanceHealth(
            @RequestParam String serviceName,
            @RequestParam String instanceId,
            @RequestParam boolean healthy) {
        boolean success = registryService.updateInstanceHealth(serviceName, instanceId, healthy);
        return ResponseEntity.ok(Map.of(
            "success", success,
            "message", success ? "健康状态更新成功" : "健康状态更新失败"
        ));
    }
    
    /**
     * 启用/禁用实例
     */
    @PutMapping("/instance/enabled")
    public ResponseEntity<Map<String, Object>> updateInstanceEnabled(
            @RequestParam String serviceName,
            @RequestParam String instanceId,
            @RequestParam boolean enabled) {
        boolean success = registryService.updateInstanceEnabled(serviceName, instanceId, enabled);
        return ResponseEntity.ok(Map.of(
            "success", success,
            "message", success ? "实例状态更新成功" : "实例状态更新失败"
        ));
    }
    
    /**
     * 清除服务缓存
     */
    @DeleteMapping("/cache/{serviceName}")
    public ResponseEntity<Map<String, Object>> clearCache(@PathVariable String serviceName) {
        discoveryClient.clearCache(serviceName);
        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "缓存清除成功"
        ));
    }
}