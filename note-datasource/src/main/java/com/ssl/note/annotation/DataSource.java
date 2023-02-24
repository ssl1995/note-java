package com.ssl.note.annotation;

import java.lang.annotation.*;

/**
 * @Author: SongShengLin
 * @Date: 2023/02/24 15:27
 * @Describe: 动态数据源注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String value();

}
