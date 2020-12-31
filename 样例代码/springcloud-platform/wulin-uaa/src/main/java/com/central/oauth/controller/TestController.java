package com.central.oauth.controller;

import com.central.common.annotation.LoginUser;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.model.SysUser;
import com.central.oauth.dto.ClientDto;
import com.central.oauth.model.Client;
import com.central.oauth.service.IClientService;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @Autowired
    private IClientService clientService;

    @GetMapping("/test")
    @ApiOperation(value = "")
    public String list(@RequestParam Map<String, Object> params, @LoginUser SysUser user) {
        return "tttttttttttttt";
    }


}
