package com.tanghongtu.advanced.p02;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashSet;
import java.util.Set;

public class SoftReferenceTest {

    static class SoftObject {
        byte[] data = new byte[1024];
    }

    public static int removedSoftRes = 0;
    public static int CACHE_INITIAL_CAPACITY = 100 * 1024;
    public static Set<SoftReference<SoftObject>> cache = new HashSet<>(CACHE_INITIAL_CAPACITY);
    public static ReferenceQueue<SoftObject> referenceQueue = new ReferenceQueue<>();

    public static void main(String[] args) {
        for (int i = 0; i < CACHE_INITIAL_CAPACITY; i++) {
            SoftObject obj = new SoftObject();
            cache.add(new SoftReference<>(obj, referenceQueue));
            cleanUselessReferences();
            if (i % 10000 == 0) {
                System.out.println("size of cache:" + cache.size());
            }
        }
        System.out.println("End! removed soft references = " + removedSoftRes);
    }

    private static void cleanUselessReferences() {
        Reference<? extends SoftObject> ref = referenceQueue.poll();
        while (ref != null) {
            if (cache.remove(ref)) {
                removedSoftRes++;
            }
            ref = referenceQueue.poll();
        }
    }

}
