package com.ssl.notebase.mall.learn.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: SongShengLin
 * @Date: 2022/08/23 15:32
 * @Describe: 添加Mybatis的java配置
 */
@Configuration
@MapperScan("com.ssl.notebase.mall.learn.mbg.mapper")
public class MyBatisConfig {

}
