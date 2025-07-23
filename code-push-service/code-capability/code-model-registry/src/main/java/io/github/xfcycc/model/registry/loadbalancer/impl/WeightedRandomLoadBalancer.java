package io.github.xfcycc.model.registry.loadbalancer.impl;

import io.github.xfcycc.model.registry.entity.ServiceInstance;
import io.github.xfcycc.model.registry.loadbalancer.LoadBalancer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * 加权随机负载均衡器
 */
@Component("weightedRandomLoadBalancer")
public class WeightedRandomLoadBalancer implements LoadBalancer {
    
    private final Random random = new Random();
    
    @Override
    public ServiceInstance choose(List<ServiceInstance> instances, String serviceName) {
        if (instances == null || instances.isEmpty()) {
            return null;
        }
        
        if (instances.size() == 1) {
            return instances.get(0);
        }
        
        // 计算总权重
        double totalWeight = instances.stream()
            .mapToDouble(instance -> instance.getWeight() != null ? instance.getWeight() : 1.0)
            .sum();
        
        if (totalWeight <= 0) {
            // 如果总权重为0，使用随机选择
            return instances.get(random.nextInt(instances.size()));
        }
        
        // 生成随机数
        double randomWeight = random.nextDouble() * totalWeight;
        
        // 根据权重选择实例
        double currentWeight = 0;
        for (ServiceInstance instance : instances) {
            currentWeight += (instance.getWeight() != null ? instance.getWeight() : 1.0);
            if (randomWeight <= currentWeight) {
                return instance;
            }
        }
        
        // 兜底返回最后一个实例
        return instances.get(instances.size() - 1);
    }
    
    @Override
    public String getName() {
        return "weighted-random";
    }
}