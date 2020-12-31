package com.central.test;

import com.central.common.model.Result;
import com.central.common.model.SysRole;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 武林
 * @date 2020/10/14 13:33
 */
@RestController
@Slf4j
@RequestMapping("/test")
public class TestttController {

    @Autowired
    private TestService testService;
    @Autowired
    private TestServiceImpl testServiceimpl;

    @PostMapping("/aaaaa")
    public Result saveOrUpdate(@RequestBody TestEntity testEntity) {
//        //非public方法事務不生效
//        testServiceimpl.adddddd();
//        //内部调用
//        testServiceimpl.testInnerInvoke();
        //捕获异常
        try{
            testServiceimpl.insertTestCatchException();
        }catch (Exception e){
            System.out.println("122222222");
        }

//        testService.add(testEntity);
        return Result.succeed("ok");
    }

    @PostMapping("/bbbbb")
    public Result saveOrUpdate3(@RequestBody TestEntity testEntity) {

        testServiceimpl.ttttttt(null);

        return Result.succeed("ok");
    }
}
