package org.apache.dubbo.demo;

import java.util.concurrent.CompletableFuture;

/**
 * Demo服务接口定义
 */
public interface DemoService {

    String sayHello(String name);

    default CompletableFuture<String> sayHelloAsync(String name) {
        return CompletableFuture.completedFuture(sayHello(name));
    }

}