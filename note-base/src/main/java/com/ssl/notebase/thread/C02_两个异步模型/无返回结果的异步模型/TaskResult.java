package com.ssl.notebase.thread.C02_两个异步模型.无返回结果的异步模型;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: SongShengLin
 * @Date: 2022/12/01 22:24
 * @Describe:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResult implements Serializable {

    private static final long serialVersionUID = -8365790794699015015L;

    private Integer taskStatus;

    private String taskMessage;

    private String taskResult;

}
