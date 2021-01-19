package com.tang.threadpool;

import java.util.concurrent.atomic.AtomicStampedReference;

public class ABAQuestion {

    /**
     * 需要这样写，才能根绝ABA问题
     */
    public static void main(String[] args) {
        final AtomicStampedReference reference = new AtomicStampedReference(1, 0);
        Thread thread1 = new Thread() {
            @Override
            public void run() {
//                try {
                    System.out.println(Thread.currentThread().getName());
                    int stamp = reference.getStamp();
                    System.out.println("改成11：" +
                            reference.compareAndSet(1, 2, stamp, stamp + 1));
                    System.out.println("当前11的值：" + reference.getReference());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                int stamp = reference.getStamp();
                System.out.println("改成12：" +
                        reference.compareAndSet(1, 2, stamp, stamp + 1));
                System.out.println("当前12的值：" + reference.getReference());
                stamp = reference.getStamp();
                System.out.println("改成10：" +
                        reference.compareAndSet(2, 1, stamp, stamp + 1));
                System.out.println("当前10的值：" + reference.getReference());
            }
        };
        thread1.start();
        thread2.start();
    }

}
