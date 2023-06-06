package com.api.project.provider;

import java.util.concurrent.CompletableFuture;

/**
 * dubbo 微服务提供者案例接口
 * @author zwh
 */
public interface DemoService {

    String sayHello(String name);

    String sayHello2(String name);

    default CompletableFuture<String> sayHelloAsync(String name) {
        return CompletableFuture.completedFuture(sayHello(name));
    }

}
