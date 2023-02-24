package com.ssl.note.config.db.support;

import com.google.common.collect.Maps;
import com.ssl.note.annotation.DataSource;
import com.ssl.note.config.db.attribute.DataSourceAttributes;
import org.apache.logging.log4j.util.Strings;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.MethodClassKey;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.util.ClassUtils;

import javax.annotation.Resource;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: SongShengLin
 * @Date: 2023/02/24 15:42
 * @Describe:
 */
public class DataSourceClassResolver {
    @Resource
    private DataSourceAttributes dataSourceAttributes;

    public DataSourceClassResolver(DataSourceAttributes dataSourceAttributes) {
        this.dataSourceAttributes = dataSourceAttributes;
    }

    private Map<Object, String> dataSourceCache = Maps.newConcurrentMap();


    public String findDataSourceKey(Method method, Object targetObject) {

        if (method.getDeclaringClass() == Object.class) {
            return Strings.EMPTY;
        }

        Object key = new MethodClassKey(method, targetObject.getClass());

        String dataSourceAnnotatedValue = dataSourceCache.get(key);

        if (dataSourceAnnotatedValue == null) {
            dataSourceAnnotatedValue = dataSourceAttributes.getScanType() == 0 ?
                    computeDatasourceKeyByAnnotated(method, targetObject) : computeDatasourceKeyByPath(method, targetObject);
            if (dataSourceAnnotatedValue == null) {
                dataSourceAnnotatedValue = Strings.EMPTY;
            }

            dataSourceCache.put(key, dataSourceAnnotatedValue);
        }

        return dataSourceAnnotatedValue;

    }


    private String computeDatasourceKeyByAnnotated(Method method, Object targetObject) {
        System.out.println("computeDatasourceKeyByAnnotated");
        System.out.println(targetObject.getClass().getName());
        if (!Modifier.isPublic(method.getModifiers())) {
            return null;
        }

        String annotatedValue = findDataSourceAnnotatedValue(method);

        if (Objects.nonNull(annotatedValue)) {
            return annotatedValue;
        }

        Class<?> targetClass = targetObject.getClass();
        Class<?> userClass = ClassUtils.getUserClass(targetClass);
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, userClass);

        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
        annotatedValue = findDataSourceAnnotatedValue(specificMethod);

        if (Objects.nonNull(annotatedValue)) {
            return annotatedValue;
        }

        annotatedValue = findDataSourceAnnotatedValue(userClass);

        return annotatedValue;
    }

    private String computeDatasourceKeyByPath(Method method, Object targetObject) {
        System.out.println("computeDatasourceKeyByPath");
        String allPath = method.getDeclaringClass().getName();
        String str = dataSourceAttributes.getBaseScanPath();
        if (allPath.startsWith(str) && allPath.length() > str.length()) {
            String[] split = allPath.substring(str.length() + 1).split("\\.");
            if (split.length > 0) {
                return split[0];
            }
        }
        return "";

    }


    private String findDataSourceAnnotatedValue(AnnotatedElement ae) {
        AnnotationAttributes attributes = AnnotatedElementUtils.getMergedAnnotationAttributes(ae, DataSource.class);
        if (attributes != null) {
            return attributes.getString("value");
        }
        return null;
    }

    public static void main(String[] args) {
//        Class<DataSourceClassResolver> dataSourceClassResolverClass = DataSourceClassResolver.class;
//        Method[] methods = dataSourceClassResolverClass.getMethods();
//        System.out.println(666);
//        System.out.println(new DataSourceClassResolver().computeDatasourceKeyByAnnotated(methods[0], new DataSourceClassResolver()));
//        System.out.println(new DataSourceClassResolver().computeDatasourceKeyByPath( new DataSourceClassResolver()));
    }
}
