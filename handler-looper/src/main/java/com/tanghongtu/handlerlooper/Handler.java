package com.tanghongtu.handlerlooper;

public class Handler {

    final Looper mLooper;
    final MessageQueue mQueue;

    public Handler(){
        mLooper = Looper.myLooper();
        if (mLooper == null) {
            throw new RuntimeException("Can't create handler inside thread " + Thread.currentThread()
                    + " that has not called Looper.prepare()");
        }
        mQueue = mLooper.mQueue;
    }

    public Handler(Looper looper) {
        mLooper = looper;
        mQueue = looper.mQueue;
    }

    public final boolean sendMessage(Message msg){
        return sendMessageDelayed(msg, 0L);
    }

    public final boolean sendMessageDelayed(Message msg, long delay) {
        msg.target = this;
        delay = Math.max(delay, 0L);
        msg.when = System.currentTimeMillis() + delay;
        return mQueue.enqueueMessage(msg);
    }

    public final boolean post(Runnable r) {
       return postDelayed(r, 0L);
    }

    public final boolean postDelayed(Runnable r, long delay){
        Message msg = new Message();
        msg.callback = r;
        return sendMessageDelayed(msg, delay);
    }

    public void dispatchMessage(Message msg) {
        if (msg.callback != null) {
            msg.callback.run();
        }else{
            handleMessage(msg);
        }
    }

    public void handleMessage(Message msg){

    }
}