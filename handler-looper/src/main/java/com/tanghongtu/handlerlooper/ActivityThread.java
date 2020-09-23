package com.tanghongtu.handlerlooper;

/**
 * CREATE BY Tanghongtu 2020/9/12
 */
public class ActivityThread {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            System.out.println(msg);
        }
    };

    public void attach() {
        Thread otherThread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 5; j++) {
                        Message msg = new Message();
                        msg.what = i * 5 + j;
                        msg.obj = "[" + i + "," + j + "]";
                        mHandler.sendMessageDelayed(msg, (long) Math.random() * 10000);
                    }
                }
            }
        };
        otherThread.start();
    }
}
