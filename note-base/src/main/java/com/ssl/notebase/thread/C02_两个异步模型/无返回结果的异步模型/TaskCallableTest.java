package com.ssl.notebase.thread.C02_两个异步模型.无返回结果的异步模型;

/**
 * @Author: SongShengLin
 * @Date: 2022/12/01 22:32
 * @Describe:
 */
public class TaskCallableTest {

    public static void main(String[] args) {
        TaskCallable<TaskResult> taskCallable = new TaskHandle();

        TaskExecutor taskExecutor = new TaskExecutor(taskCallable, "测试回调函数");

        new Thread(taskExecutor).start();
    }
}
