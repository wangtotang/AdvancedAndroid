package com.tang.threadpool;

import java.util.ArrayList;
import java.util.Collections;

public class ThreadLocalDemo {


    static class Node {

        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {

        final Node node = new Node(123);

        final ThreadLocal<Node> localA = new ThreadLocal<>();
        final ThreadLocal<Node> localB = new ThreadLocal<>();

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                localA.set(node);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("localA:" + localA.get().value);
            }
        };
        Thread thread2 = new Thread() {

            @Override
            public void run() {
                localB.set(node);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("localB:" + localB.get().value);
            }
        };
        thread1.start();
        thread2.start();
        node.value = 456;
        System.out.println("node:" + node.value);

    }

}
