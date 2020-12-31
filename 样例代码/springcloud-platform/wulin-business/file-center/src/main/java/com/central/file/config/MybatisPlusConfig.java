package com.central.file.config;

import com.central.db.config.DefaultMybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wulin
 * @date 2018/12/10
 */
@Configuration
@MapperScan({"com.central.file.mapper*"})
public class MybatisPlusConfig extends DefaultMybatisPlusConfig {

}
