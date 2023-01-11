package com.wulin.simple.server;


import com.wulin.rpc.server.RpcService;
import com.wulin.simple.api.HelloService;
import com.wulin.simple.api.Person;

@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }

    @Override
    public String hello(Person person) {
        return "Hello! " + person.getFirstName() + " " + person.getLastName();
    }
}
