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
         *
         *  CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
         *             System.out.println("任务1开始执行");
         *             Integer i = 10 / 4;
         *             System.out.println("任务1执行完成");
         *             return i;
         *         }, executor);
         *
         *         CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
         *             System.out.println("任务2开始执行");
         *             Integer i = 10 / 2;
         *             System.out.println("任务2执行完成");
         *             return i;
         *         }, executor);
         *
         *
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

        /**
         * 连个任务只要一个完成，就会执行
         *
         * runAfterEitherAsync 无法获取前两个的结果 不可以返回结果
         *  future1.runAfterEitherAsync(future2, () -> {
         *             System.out.println("任务3开始");
         *         }, executor);
         *
         * acceptEitherAsync 可以获取前两个的结果 但是不能返回结果
         *  future1.acceptEitherAsync(future2, (result1) -> {
         *             System.out.println("任务3开始执行");
         *             System.out.println("前两个执行结果之一" + result1);
         *             System.out.println("任务3执行完成");
         *         }, executor);
         *
         *  applyToEitherAsync 可以获取前两个的结果 也可以返回结果
         * CompletableFuture<Integer> integerCompletableFuture = future1.applyToEitherAsync(future2, (result1) -> {
         *             System.out.println("任务3开始执行");
         *             System.out.println("前两个任务的结果之一" + result1);
         *             System.out.println("任务3执行完成");
         *             return result1;
         *         }, executor);
         *
         */

        /**
         * 所有都完成
         * CompletableFuture.allOf(future1, future2, future3).join(); // 所有都完成才会执行
         *
         *CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(future1, future2, future3); // 只要有一个完成就会执行
         *
         *
         *
         */
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1开始执行");
            Integer i = 10 / 4;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务1执行完成");
            return i;
        }, executor);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2开始执行");
            Integer i = 10 / 2;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务2执行完成");
            return i;
        }, executor);

        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务3开始执行");
            Integer i = 10 / 2;
            System.out.println("任务3执行完成");
            return i;
        }, executor);


        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(future1, future2, future3);
        try {
            System.out.println("其中一个任务执行完成" + objectCompletableFuture.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
