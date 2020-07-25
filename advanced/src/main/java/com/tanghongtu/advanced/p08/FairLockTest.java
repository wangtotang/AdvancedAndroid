package com.tanghongtu.advanced.p08;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tanghongtu on 2020/7/25.
 */
public class FairLockTest implements Runnable {

    private int sharedNumber = 0;
    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (sharedNumber < 20) {
            lock.lock();
            try {
                sharedNumber++;
                System.out.println(Thread.currentThread().getName() + " 获得锁， sharedNumber is " + sharedNumber);
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLockTest flt = new FairLockTest();
        Thread t1 = new Thread(flt);
        Thread t2 = new Thread(flt);
        Thread t3 = new Thread(flt);
        t1.start();
        t2.start();
        t3.start();
    }
}
