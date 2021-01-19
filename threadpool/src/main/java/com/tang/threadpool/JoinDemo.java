package com.tang.threadpool;

/**
 * 线程按顺序执行
 */
public class JoinDemo {

    public static void main(String[] args) {

        final Thread thread1 = new Thread(){
            @Override
            public void run() {
                System.out.println("线程1执行");
            }
        };

        final Thread thread2 = new Thread(){
            @Override
            public void run() {
                try {
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程2执行");
            }
        };

        final Thread thread3 = new Thread(){
            @Override
            public void run() {
                try {
                    thread2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程3执行");
            }
        };
        thread1.start();
        thread2.start();
        thread3.start();
    }

}
