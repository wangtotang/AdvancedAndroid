package com.tanghongtu.handlerlooper;

/**
 * CREATE BY Tanghongtu 2020/9/12
 */
public final class Looper {

    private static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();
    final MessageQueue mQueue;

    private Looper(){
        mQueue = new MessageQueue();
    }

    public static void prepare(){
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }

    public static Looper myLooper(){
        return sThreadLocal.get();
    }

    public MessageQueue getQueue(){
        return mQueue;
    }

    public static void loop(){
        Looper me = myLooper();
        if (me == null) {
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread");
        }
        while (true){
            Message msg = me.mQueue.next();
            if (msg == null) {
                return;
            }
            msg.target.dispatchMessage(msg);
        }
    }

    public void quit(){
        mQueue.quit();
    }

}
