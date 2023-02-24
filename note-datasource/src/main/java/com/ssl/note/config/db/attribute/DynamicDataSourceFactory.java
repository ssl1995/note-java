package com.ssl.note.config.db.attribute;

import com.ssl.note.config.db.DynamicRoutingDataSource;
import com.ssl.note.config.db.aop.DynamicSourceAnnotationAdvisor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: SongShengLin
 * @Date: 2023/02/24 15:38
 * @Describe:
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(DataSourceAttributes.class)
public class DynamicDataSourceFactory {

    @Resource
    private DataSourceAttributes dataSourceAttributes;


    @Bean
    public DynamicRoutingDataSource loadDynamicDataSource() {
        Map<Object, Object> targetDataSource = dataSourceAttributes.getDatasource()
                .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey
                        , item -> item.getValue().initializeDataSourceBuilder().build()
                        , (v1, v2) -> v2));

        log.info("[dynamic data source] 加载数据源:" + targetDataSource);
        DynamicRoutingDataSource drds = new DynamicRoutingDataSource();
        drds.setTargetDataSources(targetDataSource);
        return drds;
    }


    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public Advisor dynamicDatasourceAnnotationAdvisor() {
        return new DynamicSourceAnnotationAdvisor(dataSourceAttributes);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dynamicDataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mappers/**/*.xml"));
        Objects.requireNonNull(bean.getObject()).getConfiguration().setMapUnderscoreToCamelCase(true);
        return bean.getObject();
    }

}

