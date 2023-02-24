package com.ssl.note.config.context;

import lombok.ToString;

/**
 * @Author: SongShengLin
 * @Date: 2023/02/24 15:34
 * @Describe:
 */
@ToString
public class DynamicSourceTypeThreadLocal<T> extends ThreadLocal<T> {

    private final String type;

    public DynamicSourceTypeThreadLocal(String type) {
        this.type = type;
    }

    @Override
    public void set(T value) {
        super.set(value);
    }

    @Override
    public T get() {
        return super.get();
    }
}
