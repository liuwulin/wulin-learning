package com.wulin.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/test")
public class ExampleController {

    @GetMapping("helloworld")
    public List<String> helloworld() {
        return Arrays.asList("Spring Security simple demo");
    }
}