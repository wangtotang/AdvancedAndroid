package com.tanghongtu.advanced.p02;

public class GCRooStaticVariable {

    private static int _10MB = 10 * 1024 * 1024;
    private byte[] memory;
    private static GCRooStaticVariable staticVariable;

    public GCRooStaticVariable(int size) {
        memory = new byte[size];
    }

    public static void main(String[] args) {
        System.out.println("程序開始");
        printMemory();
        GCRooStaticVariable g = new GCRooStaticVariable(4 * _10MB);
        g.staticVariable = new GCRooStaticVariable(8 * _10MB);
        g = null;
        System.gc();
        System.out.println("GC完成");
        printMemory();
    }

    /**
     * 打印当前JVM剩余空间和总的空间大小
     */
    private static void printMemory() {

        System.out.print("free is " + Runtime.getRuntime().freeMemory()/1024/1024 + " M, ");
        System.out.println("total is " + Runtime.getRuntime().totalMemory()/1024/1024 + " M, ");

    }

}
