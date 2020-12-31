package com.central.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 应用相关接口
 *
 * @author zlt
 */
@Api(tags = "应用")
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    @ApiOperation(value = "")
    public String list(@RequestParam Map<String, Object> params) {
        return "tttttttttttttt";
    }


}
