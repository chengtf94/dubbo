package org.apache.dubbo.demo.provider;

import org.apache.dubbo.demo.GreetingService;

/**
 * 招呼服务接口实现
 */
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String hello() {
        return "Greetings!";
    }

}
