package com.tanghongtu.advanced.p08;

/**
 * Created by tanghongtu on 2020/7/25.
 */
public class SynchronizedTest {

    private int sum = 0;

    public synchronized void calculate() {
        sum = sum + 1;
    }

    public static void main(String[] args) {

        final SynchronizedTest test1 = new SynchronizedTest();
        final SynchronizedTest test2 = new SynchronizedTest();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test1.printLog();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test2.printLog();
            }
        });
        t1.start();
        t2.start();
    }

    //修饰实例方法，实例之间不互斥
    public synchronized void printLog() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is printing " + i);
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //修饰静态方法，实例之间互斥
    public static synchronized void printLog2() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is printing " + i);
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private final Object lock = new Object();

    //修饰代码块,任一对象都可以作为锁对象
    public void printLog3() {
        try {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + " is printing " + i);
                    Thread.sleep(300);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
