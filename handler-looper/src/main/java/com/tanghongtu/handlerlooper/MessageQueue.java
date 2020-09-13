package com.tanghongtu.handlerlooper;

import java.util.concurrent.DelayQueue;

/**
 * CREATE BY Tanghongtu 2020/9/12
 */
public class MessageQueue {

    private DelayQueue<Message> mQueue = new DelayQueue<>();

    public Message next() {
        try {
            return mQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void quit() {
        mQueue.clear();
    }

    public boolean enqueueMessage(Message msg) {
        return mQueue.add(msg);
    }
}
