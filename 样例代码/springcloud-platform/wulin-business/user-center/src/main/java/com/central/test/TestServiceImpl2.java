package com.central.test;

import com.central.test.config.BizException;
import com.central.user.mapper.TestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author 武林
 * @date 2020/10/14 13:34
 */
@Slf4j
@Service(value = "TestServiceImpl2")
public class TestServiceImpl2 {

    @Resource
    private TestMapper testMapper;

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.MANDATORY)
    public  void add(TestEntity testEntity) {

        testMapper.insert(new TestEntity(UUID.randomUUID().toString().replace("-",""),"1","100"));
//        System.out.println(1/0);
    }


}
