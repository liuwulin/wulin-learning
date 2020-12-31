package com.example.jpa.redis;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Liu WuLin
 * @date 101 11:42
 */
@RestController
public class TestController {


    @Autowired
    private RedissonClient client;

    @PostMapping("test")
    public void init() {
        System.out.println("---------------------------------");
        RBucket<String> bucket =  client.getBucket("tttttttttttttttttttttt");
        bucket.set("uuuuuuuuuuu");
        String aaa = bucket.get();
        System.out.println(aaa);


        return ;
    }



}
