package com.tanghongtu.advanced.p02;

public class GCRootThread {

    private int _10MB = 10 * 1024 * 1024;
    private byte[] memory = new byte[8 * _10MB];

    public static void main(String[] args) throws InterruptedException {
        System.out.println("開始前内存情況：");
        printMemory();
        AsyncTask at = new AsyncTask(new GCRootThread());
        Thread thread = new Thread(at);
        thread.start();
        System.gc();
        System.out.println("main方法執行完畢，完成GC");
        printMemory();

        thread.join();
        at = null;
        System.gc();
        System.out.println("綫程代碼執行完畢，完成GC");
        printMemory();

    }

    private static class AsyncTask implements Runnable {
        private GCRootThread gcRootThread;

        public AsyncTask(GCRootThread gcRootThread) {
            this.gcRootThread = gcRootThread;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            }catch (Exception e){

            }
        }
    }

    /**
     * 打印当前JVM剩余空间和总的空间大小
     */
    private static void printMemory() {

        System.out.print("free is " + Runtime.getRuntime().freeMemory()/1024/1024 + " M, ");
        System.out.println("total is " + Runtime.getRuntime().totalMemory()/1024/1024 + " M, ");

    }

}
