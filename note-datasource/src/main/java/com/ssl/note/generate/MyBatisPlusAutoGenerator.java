//package com.ssl.note.generate;
//
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.config.OutputFile;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import java.util.Collections;
//
///**
// * @Author: SongShengLin
// * @Date: 2022/11/28 22:31
// * @Describe:
// */
//public class MyBatisPlusAutoGenerator {
//    public static final String TABLE_NAME = "order";
//
//
//    /**
//     * mybatis-plus逆向生成方法
//     */
//    public static void main(String[] args) {
//
//        FastAutoGenerator.create("jdbc:mysql://rm-2zep5730lmk5s5dy37o.mysql.rds.aliyuncs.com:3306/seata-demo?characterEncoding=UTF8&autoReconnect=true&serverTimezone=Asia/Shanghai&allowMultiQueries=true",
//                        "root", "Ssl@199502")
//                .globalConfig(builder -> {
//                    builder.author("songshenglin").fileOverride()
//                            .outputDir("/Users/didi/jetbrains/ideaProduct/note-spring-clooud-alibaba/seata-order-8801/src/main/java");
//                })
//                .packageConfig(builder -> {
//                    builder.parent("com.ssl.note.seata.order")
//                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,
//                                    "/Users/didi/jetbrains/ideaProduct/note-spring-clooud-alibaba/seata-order-8801/src/main/java/com/ssl/note/seata/order/mapper"));
//                })
//                .strategyConfig(builder -> {
//                    // 指定逆向生成表
//                    builder.addInclude(TABLE_NAME);
//                })
//                .templateEngine(new FreemarkerTemplateEngine())
//                .execute();
//    }
//
//}
