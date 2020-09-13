package com.tanghongtu.handlerlooper;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * CREATE BY Tanghongtu 2020/9/12
 */
public class Message implements Delayed {

    Handler target;
    public int what;
    public int arg1;
    public int arg2;
    public Object obj;
    long when;
    Runnable callback;

    @Override
    public long getDelay(TimeUnit timeUnit) {
        return timeUnit.convert(when - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed delayed) {
        if (delayed instanceof Message) {
            return (int) (when - ((Message) delayed).when);
        } else {
            return (int) (getDelay(TimeUnit.MILLISECONDS) - delayed.getDelay(TimeUnit.MILLISECONDS));
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                ", what=" + what +
                ", arg1=" + arg1 +
                ", arg2=" + arg2 +
                ", obj=" + obj +
                ", when=" + when +
                '}';
    }
}
