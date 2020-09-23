package com.tanghongtu.handlerlooper;

/**
 * CREATE BY Tanghongtu 2020/9/12
 */
public class Test {

    public static void main(String[] args) {

        Looper.prepare();

        ActivityThread activityThread = new ActivityThread();
        activityThread.attach();

        Looper.loop();
    }

}
