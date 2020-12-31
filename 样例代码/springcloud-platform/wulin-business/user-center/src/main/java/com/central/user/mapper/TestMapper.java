package com.central.user.mapper;

import com.central.common.model.SysRole;
import com.central.db.mapper.SuperMapper;
import com.central.test.TestEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 武林
 * @date 2020/10/14 13:34
 */
@Mapper
public interface TestMapper extends SuperMapper<TestEntity> {
}
