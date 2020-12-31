package com.dubbo.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liu WuLin
 * @date 2020 - 04 - 03 11:05
 */
@RestController
@RequestMapping("/test")
public class DemoController {

    @Reference
    private  DemoService demoService;

    @HystrixCommand(fallbackMethod = "test1")
    @GetMapping("/test")
    public void test() {

        System.out.println(demoService.sayHello("wou"));
    }

    public void test1() {
        System.out.println("这是容错回调！");
    }
}
