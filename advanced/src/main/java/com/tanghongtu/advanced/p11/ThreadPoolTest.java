package com.tanghongtu.advanced.p11;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by tanghongtu on 2020/7/28.
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
//        runSingleThread();
//        runCachedThread();
//        ruFixedThread();
        runScheduledThread();
    }

    public static void runSingleThread() {
        ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            singleExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程： " + Thread.currentThread().getName() + " 正在执行 tasks: " + taskId);
                }
            });
        }
    }

    public static void runCachedThread() throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            Thread.sleep(1000);
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("线程： " + Thread.currentThread().getName() + " 正在执行 tasks: " + taskId);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        cachedThreadPool.shutdown();
    }

    public static void ruFixedThread() throws InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            fixedThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("线程： " + Thread.currentThread().getName() + " 正在执行 tasks: " + taskId);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        fixedThreadPool.shutdown();
    }

    public static void runScheduledThread() throws InterruptedException {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Date date = new Date();
                System.out.println("线程： " + Thread.currentThread().getName() + " 报时 : " + date);
            }
        }, 500, 500, TimeUnit.MILLISECONDS);
        Thread.sleep(5000);
        scheduledThreadPool.shutdown();
    }

}
