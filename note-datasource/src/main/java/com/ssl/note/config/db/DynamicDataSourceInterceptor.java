package com.ssl.note.config.db;

import com.ssl.note.config.context.DynamicDataSourceContextHolder;
import com.ssl.note.config.db.attribute.DataSourceAttributes;
import com.ssl.note.config.db.support.DataSourceClassResolver;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * @Author: SongShengLin
 * @Date: 2023/02/24 15:39
 * @Describe:
 */
@Slf4j
public class DynamicDataSourceInterceptor implements MethodInterceptor, Serializable {

    private final DataSourceClassResolver dataSourceClassResolver;

    private DataSourceAttributes dataSourceAttributes;

    public DynamicDataSourceInterceptor(DataSourceAttributes dataSourceAttributes) {
        this.dataSourceAttributes = dataSourceAttributes;
        this.dataSourceClassResolver = new DataSourceClassResolver(dataSourceAttributes);
    }


    @Nullable
    @Override
    public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
        String dataSourceKey = dataSourceClassResolver.findDataSourceKey(invocation.getMethod(), invocation.getThis());

        if (StringUtils.isEmpty(dataSourceKey)) {
            dataSourceKey = dataSourceAttributes.getDefaultSource();
        }
        log.info("[dynamic data source] 开始设置数据源" + dataSourceKey);
        try {
            DynamicDataSourceContextHolder.push(dataSourceKey);
            return invocation.proceed();
        } finally {
            DynamicDataSourceContextHolder.poll();
        }
    }
}
