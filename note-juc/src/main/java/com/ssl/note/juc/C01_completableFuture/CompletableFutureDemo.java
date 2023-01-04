package com.ssl.note.juc.C01_completableFuture;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @Author: SongShengLin
 * @Date: 2023/01/02 11:37
 * @Describe:
 */
public class CompletableFutureDemo {

    /**
     * CompletableFuture的静态方法:提供异步方法
     * Java8提供更灵活的API
     */
    @Test
    public void initAsyncTest() throws ExecutionException, InterruptedException {
        // CompletableFuture的静态方法
        // supplyAsync：有返回值 = callable
        // runAsync: 无返回值 = runnable
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t" + "-----come in");
                    int result = ThreadLocalRandom.current().nextInt(10);
                    //暂停几秒钟线程
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("-----计算结束耗时1秒钟，result： " + result);
                    if (result > 6) {
                        int age = 10 / 0;
                    }
                    return result;
                })// 自动回调
                .whenComplete((v, e) -> {
                    if (e == null) {
                        System.out.println("-----result: " + v);
                    }
                }).exceptionally(e -> {
                    System.out.println("-----exception: " + e.getCause() + "\t" + e.getMessage());
                    return -44;
                });

        Integer getRes = completableFuture.get();
        System.out.println(getRes);

        //主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:暂停3秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * join
     */
    @Test
    public void jonTest() {
        System.out.println(CompletableFuture.supplyAsync(() -> "abc").thenApply(r -> r + "123")
                .join());
    }

    /**
     * complete
     */
    @Test
    public void completeTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 533;
        });

        //注释掉暂停线程，get还没有算完只能返回complete方法设置的444；暂停2秒钟线程，异步线程能够计算完成返回get
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //当调用CompletableFuture.get()被阻塞的时候,complete方法就是结束阻塞并get()获取设置的complete里面的值.
        // false	533
        System.out.println(completableFuture.complete(444) + "\t" + completableFuture.get());
    }


    /**
     * thenApply:计算结果存在依赖关系，如果有异常，就用exceptionally捕获；有返回值
     */
    @Test
    public void thenApplyTest() throws ExecutionException, InterruptedException {
        //当一个线程依赖另一个线程时用 thenApply 方法来把这两个线程串行化,
        CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("111");
            return 1024;
        }).thenApply(f -> {
            System.out.println("222");
            return f + 1;
        }).thenApply(f -> {
            int age = 10 / 0; // 异常情况：那步出错就停在那步。
            System.out.println("333");
            return f + 1;
        }).whenCompleteAsync((v, e) -> {
            System.out.println("*****v: " + v);
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });

        System.out.println("-----主线程结束，END");

        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * handle:计算结果存在依赖关系，如果有异常，不补货，继续往下走，有返回值
     */
    @Test
    public void handleTest() throws ExecutionException, InterruptedException {
        // 当一个线程依赖另一个线程时用 handle 方法来把这两个线程串行化,
        // 异常情况：有异常也可以往下一步走，根据带的异常参数可以进一步处理
        CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("111");
            return 1024;
        }).handle((f, e) -> {
            int age = 10 / 0;
            System.out.println("222");
            return f + 1;
        }).handle((f, e) -> {
            System.out.println("333");
            return f + 1;
        }).whenCompleteAsync((v, e) -> {
            System.out.println("*****v: " + v);
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });

        System.out.println("-----主线程结束，END");

        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * thenAccept:接受上述处理的结果，无返回值
     */
    @Test
    public void thenAcceptTest() throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(f -> f + 2)
                .thenApply(f -> f + 3).thenApply(f -> f + 4)
                .thenAccept(System.out::println);
    }

    /**
     * applyToEither:哪个返回快，用哪个
     */
    @Test
    public void applyToEitherTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in ");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in ");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 20;
        });

        CompletableFuture<Integer> thenCombineResult = completableFuture1.applyToEither(completableFuture2, f -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in ");
            return f + 1;
        });

        System.out.println(Thread.currentThread().getName() + "\t" + thenCombineResult.get());
    }

    /**
     * thenCombine:两个异步线程执行结果一起处理
     * 先完成的先等着，等待其它分支任务
     */
    @Test
    public void thenCombineTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> thenCombineResult = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in 1");
            return 10;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in 2");
            return 20;
        }), (x, y) -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in 3");
            return x + y;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in 4");
            return 30;
        }), (a, b) -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in 5");
            return a + b;
        });
        System.out.println("-----主线程结束，END");
        System.out.println(thenCombineResult.get());

        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


