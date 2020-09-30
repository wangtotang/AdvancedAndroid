package com.tang.threadpool;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sun.misc.Unsafe;

public class MyClass {

    private static Unsafe unsafe;
    private static final long valueOffset;
    private volatile int value;
    private static ExecutorService executorService;

    static {
        try {
            final PrivilegedExceptionAction<Unsafe> action = new PrivilegedExceptionAction<Unsafe>() {
                public Unsafe run() throws Exception {
                    Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
                    theUnsafe.setAccessible(true);
                    return (Unsafe) theUnsafe.get(null);
                }
            };
            unsafe = AccessController.doPrivileged(action);
            valueOffset = unsafe.objectFieldOffset(MyClass.class.getDeclaredField("value"));
        } catch (Exception e) {
            throw new RuntimeException("Unable to load unsafe", e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        executorService = Executors.newFixedThreadPool(5);
        final MyClass myClass = new MyClass();
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    myClass.compareAndSet(10 + num);
                    System.out.println("value: " + myClass.value);
                }
            });
        }
        executorService.shutdown();
    }

    public void compareAndSet(int value) {
        int oldValue;
        do {
            oldValue = unsafe.getIntVolatile(this, valueOffset);
        } while (!unsafe.compareAndSwapInt(this, valueOffset, oldValue, value));
    }

}