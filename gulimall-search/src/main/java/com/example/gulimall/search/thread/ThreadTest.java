package com.example.gulimall.search.thread;

import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.util.concurrent.DefaultThreadFactory;
import io.swagger.models.auth.In;

import java.util.concurrent.*;

public class ThreadTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//                    System.out.println("任务1开始执行");
//                    Integer i = 10 / 0;
//                    System.out.println("执行完成");
//                    return i;
//                }, executor);
//                .whenComplete((result, throwable) -> {
//            System.out.println("执行完成"+result);
//        }).exceptionally(ex -> {
//            System.out.println("执行异常"+ex);
//            return 0;
//        });
//        .handle((result, throwable) -> {
//            System.out.println("执行完成" + result);
//            if (throwable != null) {
//                System.out.println("执行异常" + throwable);
//                return 10;
//            }
//            return 11;
//        });

        /**
         * thenRunAsync 不能获取到结果
         * .thenRunAsync(() -> {
         *             System.out.println("任务2开始执行");
         *         }, executor);
         *
         *thenAcceptAsync 可以获取到结果 但是不能返回结果
         * .thenAcceptAsync(result -> {
         *             System.out.println("任务2开始执行" + result);
         *         }, executor);
         *
         * thenApplyAsync 可以获取到结果 也可以返回结果
         * .thenApplyAsync(res -> {
         *             System.out.println("任务2开始执行" + res);
         *             return "hello " + res;
         *         });
         */


        /**
         * 连个任务都完成
         * runAfterBothAsync 无法获取前两个的结果
         * runAfterBothAsync(future2, () -> {
         *             System.out.println("任务3开始");
         *         }, executor);
         *
         *thenAcceptBothAsync 可以获取前两个的结果 但是不能返回结果
         * thenAcceptBothAsync(future2, (result1, result2) -> {
         *             System.out.println("任务3开始执行");
         *             System.out.println("任务1执行结果" + result1);
         *             System.out.println("任务2执行结果" + result2);
         *             System.out.println("任务3执行完成" + result1 + result2);
         *         }, executor);
         *
         * thenCombineAsync 可以获取前两个的结果 也可以返回结果
         *.thenCombineAsync(future2, (result1, result2) -> {
         *             System.out.println("任务3开始执行");
         *             System.out.println("任务1执行结果" + result1);
         *             System.out.println("任务2执行结果" + result2);
         *             System.out.println("任务3执行完成" + (result1 + result2));
         *             return result1 + result2;
         *         });
         *
         */
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1开始执行");
            Integer i = 10 / 4;
            System.out.println("任务1执行完成");
            return i;
        }, executor);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2开始执行");
            Integer i = 10 / 2;
            System.out.println("任务2执行完成");
            return i;
        }, executor);

        CompletableFuture<Integer> completableFuture = future1.thenCombineAsync(future2, (result1, result2) -> {
            System.out.println("任务3开始执行");
            System.out.println("任务1执行结果" + result1);
            System.out.println("任务2执行结果" + result2);
            System.out.println("任务3执行完成" + (result1 + result2));
            return result1 + result2;
        });
        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("主线程结束");
    }
}
