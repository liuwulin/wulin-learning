package com.central.test;

import com.central.test.config.BizException;
import com.central.user.mapper.TestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author 武林
 * @date 2020/10/14 13:34
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestMapper testMapper;


    @Override
    @Transactional
    public void add(TestEntity testEntity) {
        testMapper.insert(new TestEntity(UUID.randomUUID().toString().replace("-", ""), "1", "100"));
        throw new RuntimeException();
    }

    /**
     * 非public方法事務不生效
     */
    @Transactional
    void adddddd() {
        testMapper.insert(new TestEntity(UUID.randomUUID().toString().replace("-", ""), "1", "100"));
        System.out.println(1 / 0);
    }

    /**
     * 類內部調用註解不生效
     */

    public void testInnerInvoke() {
        //类内部调用@Transactional标注的方法。
        insertTestInnerInvoke();
    }

    @Transactional
    public void insertTestInnerInvoke() {
        testMapper.insert(new TestEntity(UUID.randomUUID().toString().replace("-", ""), "1", "100"));
        System.out.println(1 / 0);
    }

    @Transactional
    public void insertTestCatchException()  {

        testMapper.insert(new TestEntity(UUID.randomUUID().toString().replace("-", ""), "1", "100"));
        throw new RuntimeException();

    }


    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    void sssssss() {
        testMapper.insert(new TestEntity(UUID.randomUUID().toString().replace("-", ""), "1", "100"));
        System.out.println(1 / 0);
    }

    @Autowired
    TestServiceImpl2 testServiceImpl2;

    //    @Transactional(rollbackFor = Exception.class)
    public void ttttttt(TestEntity testEntity) {
        testServiceImpl2.add(null);
    }


}
