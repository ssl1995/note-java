package com.ssl.note.config.context;

import org.springframework.util.Assert;

import java.util.LinkedList;

/**
 * @Author: SongShengLin
 * @Date: 2023/02/24 15:33
 * @Describe:
 */
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<LinkedList<String>> LOOKUP_KEY_HOLDER = new DynamicSourceTypeThreadLocal<>("dynamic-datasource") {
        @Override
        protected LinkedList<String> initialValue() {
            return new LinkedList<>();
        }
    };

    private DynamicDataSourceContextHolder() {
    }

    public static String peek() {
        return LOOKUP_KEY_HOLDER.get().peek();
    }

    public static void push(String dataSource) {
        Assert.hasText(dataSource, "数据源不能为空");
        LOOKUP_KEY_HOLDER.get().push(dataSource);
    }

    public static String poll() {
        return LOOKUP_KEY_HOLDER.get().poll();
    }

    public static void clear() {
        LOOKUP_KEY_HOLDER.remove();
    }
}
