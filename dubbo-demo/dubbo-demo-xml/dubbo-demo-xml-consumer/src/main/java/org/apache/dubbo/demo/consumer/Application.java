package org.apache.dubbo.demo.consumer;

import org.apache.dubbo.demo.DemoService;
import org.apache.dubbo.demo.GreetingService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CompletableFuture;

/**
 * 服务消费方：也就是调用方客户端
 */
public class Application {
    /**
     * In order to make sure multicast registry works, need to specify '-Djava.net.preferIPv4Stack=true' before
     * launch the application
     */
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-consumer.xml");
        context.start();
        DemoService demoService = context.getBean("demoService", DemoService.class);
        GreetingService greetingService = context.getBean("greetingService", GreetingService.class);

        while (true) {
            String hello = demoService.sayHello("world");
            System.out.println("result: " + hello);
            Thread.sleep(2000L);
        }

//        new Thread(() -> {
//            while (true) {
//                String greetings = greetingService.hello();
//                System.out.println(greetings + " from separated thread.");
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

//        while (true) {
//            CompletableFuture<String> hello = demoService.sayHelloAsync("world");
//            System.out.println("result: " + hello.get());
//
//            String greetings = greetingService.hello();
//            System.out.println("result: " + greetings);
//
//            Thread.sleep(500);
//        }

    }
}
