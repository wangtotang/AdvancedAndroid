package com.tanghongtu.advanced.p11;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by tanghongtu on 2020/7/28.
 */
public class ScheduledThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("线程 "+ Thread.currentThread().getName() + "执行任务task" + i);
                }
            }
        }, 500, 500, TimeUnit.MILLISECONDS);

        Thread.sleep(5000);
        scheduledExecutorService.shutdown();
    }

}
