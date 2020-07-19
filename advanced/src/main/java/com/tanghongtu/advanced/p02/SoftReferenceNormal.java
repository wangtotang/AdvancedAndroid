package com.tanghongtu.advanced.p02;

import java.lang.ref.SoftReference;

public class SoftReferenceNormal {

    static class SoftObject {
        byte[] data = new byte[120 * 1024 * 1024];
    }

    public static void main(String[] args) {
        SoftReference<SoftObject> cacheRef = new SoftReference<>(new SoftObject());
        System.out.println("第一次GC前 軟引用： " + cacheRef.get());
        System.gc();
        System.out.println("第一次GC后 軟引用：  " + cacheRef.get());

        SoftObject newSo = new SoftObject();
        System.out.println("再次分配120M強引用對象之後 軟引用：" + cacheRef.get());
    }

}
