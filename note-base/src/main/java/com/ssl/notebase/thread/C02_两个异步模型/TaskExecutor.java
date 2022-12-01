package com.ssl.notebase.thread.C02_两个异步模型;

/**
 * @Author: SongShengLin
 * @Date: 2022/12/01 22:28
 * @Describe:
 */
public class TaskExecutor implements Runnable {

    private TaskCallable<TaskResult> taskCallable;

    private String taskParameter;

    public TaskExecutor(TaskCallable<TaskResult> taskCallable, String taskParameter) {
        this.taskCallable = taskCallable;
        this.taskParameter = taskParameter;
    }

    @Override
    public void run() {
        // 处理完业务逻辑，封装需要异步调用的数据
        TaskResult result = new TaskResult();
        result.setTaskStatus(1);
        result.setTaskMessage(this.taskParameter);
        result.setTaskResult("异步回调成功");
        // 异步回调
        taskCallable.callable(result);
    }
}
