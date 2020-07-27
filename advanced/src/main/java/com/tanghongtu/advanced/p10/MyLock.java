package com.tanghongtu.advanced.p10;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by tanghongtu on 2020/7/27.
 */
public class MyLock {

    private Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public void unlock() {
        sync.release(1);
    }

    static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int i) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int i) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
    }

}
