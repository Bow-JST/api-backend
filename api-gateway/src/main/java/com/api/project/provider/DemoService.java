package com.api.project.provider;

import java.util.concurrent.CompletableFuture;

/**
 * 演示服务
 */
public interface DemoService {

    String sayHello(String name);

    String sayHello2(String name);

    /**
     * 异步调用 sayHello
     * @param name
     * @return
     */
    default CompletableFuture<String> sayHelloAsync(String name) {
        return CompletableFuture.completedFuture(sayHello(name));
    }

}
