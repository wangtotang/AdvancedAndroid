package com.tang.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {

    public static void main(String[] args) {
        /**
         * 信号量用来控制方法并发被线程访问的个数
         */
        final Semaphore semaphore = new Semaphore(2);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        /**
                         * 信号量减1
                         */
                        semaphore.acquire();
                        System.out.println("线程：" + Thread.currentThread().getName() + "获得许可：" + index);
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("允许Task个数:" + semaphore.availablePermits());
                        /**
                         * 信号量加1
                         */
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        /**
         * shutdown都不会让线程中断，只会关闭线程池，并且标记线程的中断状态，需要自行判断是否中断从而停止线程
         */
        service.shutdown();
    }

}
