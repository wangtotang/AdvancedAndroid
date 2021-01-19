package com.tang.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 生产者与消费者模型可以使用阻塞队列来实现
 */
public class Producer_Consumer {

    public static BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        producer.start();
        consumer.start();
    }

    static class Producer extends Thread {

        @Override
        public void run() {
            while (true) {
                produce();
            }
        }

        public void produce() {
            try {
                queue.add(1);
                System.out.println("生产了一件商品，商品数量：" + queue.size());
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class Consumer extends Thread {

        @Override
        public void run() {
            while (true) {
                consumer();
            }
        }

        public void consumer() {
            try {
                Thread.sleep(1000);
                if (!queue.isEmpty()) {
                    queue.remove(1);
                    System.out.println("消费了一件商品，商品数量：" + queue.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
