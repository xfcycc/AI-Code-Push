package io.github.xfcycc.model.registry.loadbalancer.impl;

import io.github.xfcycc.model.registry.entity.ServiceInstance;
import io.github.xfcycc.model.registry.loadbalancer.LoadBalancer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询负载均衡器
 */
@Component("roundRobinLoadBalancer")
public class RoundRobinLoadBalancer implements LoadBalancer {
    
    private final ConcurrentHashMap<String, AtomicInteger> counters = new ConcurrentHashMap<>();
    
    @Override
    public ServiceInstance choose(List<ServiceInstance> instances, String serviceName) {
        if (instances == null || instances.isEmpty()) {
            return null;
        }
        
        if (instances.size() == 1) {
            return instances.get(0);
        }
        
        AtomicInteger counter = counters.computeIfAbsent(serviceName, k -> new AtomicInteger(0));
        int index = counter.getAndIncrement() % instances.size();
        
        return instances.get(index);
    }
    
    @Override
    public String getName() {
        return "round-robin";
    }
}