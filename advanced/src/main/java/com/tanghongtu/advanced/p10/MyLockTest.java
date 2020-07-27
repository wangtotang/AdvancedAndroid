package com.tanghongtu.advanced.p10;

/**
 * Created by tanghongtu on 2020/7/27.
 */
public class MyLockTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread());
        Thread t2 = new Thread(new MyThread());
        Thread t3 = new Thread(new MyThread());
        t1.start();
        t2.start();
        t3.start();
    }

    private static class MyThread implements Runnable {

        static int count = 0;
        static MyLock lock = new MyLock();

        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    count++;
                    Thread.sleep(300);
                    System.out.println(Thread.currentThread().getName() + " count==>" + count);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

}
