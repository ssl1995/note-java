package com.ssl.notebase.thread.C01_join保证顺序;

import java.util.concurrent.Callable;

/**
 * @Author: SongShengLin
 * @Date: 2022/12/01 22:11
 * @Describe:
 */
public class ThreadImpl implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("进入了");


        return "调度成功！执行完毕";
    }


    public static void main(String[] args) throws Exception {
        ThreadImpl thread = new ThreadImpl();
        String call = thread.call();

    }
}
