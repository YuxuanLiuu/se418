package com.se418.consumerproducer;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


public class Consumer implements Runnable {
    private BlockingDeque<Long> container;
    private AtomicInteger successNo;
    private AtomicLong timeSum;
    int threshold;

    public Consumer(BlockingDeque<Long> container, AtomicInteger successNo, AtomicLong timeSum, int threshold) {
        this.container = container;
        this.successNo = successNo;
        this.timeSum = timeSum;
        this.threshold = threshold;
    }

    @Override
    public void run() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        System.out.println("start producer thread: " + Thread.currentThread().getId());
        try {
            while (true) {
                if (container.size() < threshold) {
                    try {
                        System.out.println("FIFO");
                        Long inTime = container.takeFirst();
                        successNo.getAndIncrement();
                        System.out.println("add item succeed");
                        timeSum.addAndGet(System.currentTimeMillis() - inTime);
                        System.out.println("remove item succeed");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("remove item failed");
                    }

                    Thread.sleep(threadLocalRandom.nextInt(2000));

                } else {
                    try {
                        System.out.println("LIFO");
                        Long inTime = container.takeLast();
                        successNo.getAndIncrement();
                        System.out.println("add item succeed");
                        timeSum.addAndGet(System.currentTimeMillis() - inTime);
                        System.out.println("remove item succeed");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("remove item failed");
                    }

                    Thread.sleep(threadLocalRandom.nextInt(2000));

                }

            }
        } catch (InterruptedException e) {

        }
    }
}
