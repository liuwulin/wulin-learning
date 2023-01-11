package com.wulin.simple.server;


import com.wulin.rpc.server.RpcService;
import com.wulin.simple.api.HelloService;
import com.wulin.simple.api.Person;

@RpcService(value = HelloService.class, version = "sample.hello2")
public class HelloServiceImpl2 implements HelloService {

    @Override
    public String hello(String name) {
        return "你好! " + name;
    }

    @Override
    public String hello(Person person) {
        return "你好! " + person.getFirstName() + " " + person.getLastName();
    }
}
