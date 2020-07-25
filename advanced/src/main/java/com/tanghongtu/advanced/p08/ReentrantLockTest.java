package com.tanghongtu.advanced.p08;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tanghongtu on 2020/7/25.
 */
public class ReentrantLockTest {

    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        final ReentrantLockTest ll = new ReentrantLockTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ll.printLog();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ll.printLog();
            }
        }).start();
    }

    public void printLog() {
        try {
            lock.lock();
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is  printing " + i);
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
