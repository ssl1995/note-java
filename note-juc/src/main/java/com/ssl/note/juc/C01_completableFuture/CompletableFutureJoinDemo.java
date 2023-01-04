package com.ssl.note.juc.C01_completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author: SongShengLin
 * @Date: 2023/01/03 22:49
 * @Describe:
 */
public class CompletableFutureJoinDemo {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(CompletableFuture.supplyAsync(() -> "abc").thenApply(r -> r + "123").join());
    }
}
