package com.ssl.notebase.thread.C02_两个异步模型.无返回结果的异步模型;

/**
 * 回调接口
 */
public interface TaskCallable<T> {

    T callable(T t);
}
