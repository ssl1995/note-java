package com.ssl.notebase.thread.C02_两个异步模型.无返回结果的异步模型;

/**
 * @Author: SongShengLin
 * @Date: 2022/12/01 22:27
 * @Describe: 实现回调函数
 */
public class TaskHandle implements TaskCallable<TaskResult> {

    @Override
    public TaskResult callable(TaskResult taskResult) {
        // 回调函数处理taskResult，根据自己业务需要进行回调
        System.out.println(taskResult);
        return taskResult;
    }
}
