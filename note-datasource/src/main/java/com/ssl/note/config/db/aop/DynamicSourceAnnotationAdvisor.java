package com.ssl.note.config.db.aop;

import com.ssl.note.config.db.DynamicDataSourceInterceptor;
import com.ssl.note.config.db.attribute.DataSourceAttributes;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.AbstractExpressionPointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;

/**
 * @Author: SongShengLin
 * @Date: 2023/02/24 15:40
 * @Describe:
 */
@Slf4j
public class DynamicSourceAnnotationAdvisor extends AbstractPointcutAdvisor {

    private DataSourceAttributes dataSourceAttributes;

    public DynamicSourceAnnotationAdvisor(DataSourceAttributes dataSourceAttributes) {
        this.dataSourceAttributes = dataSourceAttributes;
    }

    @Override
    public Pointcut getPointcut() {
        AbstractExpressionPointcut pointcut = new AspectJExpressionPointcut();
        log.info("[dynamic data source] 开始设置切入点表达式:" + "execution(public * " + dataSourceAttributes.getBaseScanPath() + "..*(..))");
        pointcut.setExpression("execution(public * " + dataSourceAttributes.getBaseScanPath() + "..*(..))");
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return new DynamicDataSourceInterceptor(dataSourceAttributes);
    }

}
