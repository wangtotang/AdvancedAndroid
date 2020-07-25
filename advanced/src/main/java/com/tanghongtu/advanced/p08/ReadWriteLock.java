package com.tanghongtu.advanced.p08;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by tanghongtu on 2020/7/25.
 */
public class ReadWriteLock {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(false);
    private static String number = "0";

    public static void main(String[] args) {
        Thread t1 = new Thread(new Reader(), "读线程 1");
        Thread t2 = new Thread(new Reader(), "读线程 2");
        Thread t3 = new Thread(new Writer(), "写线程");

        t1.start();
        t2.start();
        t3.start();
    }

    static class Writer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    lock.writeLock().lock();
                    System.out.println(Thread.currentThread().getName() + " 开始写 " + i);
                    number = number.concat("" + i);
                    Thread.sleep(300);
                    System.out.println(Thread.currentThread().getName() + " 结束写 " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.writeLock().unlock();
                }
            }
        }
    }

    static class Reader implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    lock.readLock().lock();
                    System.out.println(Thread.currentThread().getName() + " 开始读 " + number);
                    Thread.sleep(300);
                    System.out.println(Thread.currentThread().getName() + " 结束读 " + number);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.readLock().unlock();
                }
            }
        }
    }


}
