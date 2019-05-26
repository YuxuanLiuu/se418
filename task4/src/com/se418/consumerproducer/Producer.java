package com.se418.consumerproducer;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    private BlockingDeque<Long> container;
    private AtomicInteger itemNo;

    public Producer(BlockingDeque<Long> container, AtomicInteger itemNo) {
        this.container = container;
        this.itemNo = itemNo;
    }

    @Override
    public void run() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        System.out.println("start producer thread: " + Thread.currentThread().getId());
        try {
            while (true) {
                try {
                    container.putLast(System.currentTimeMillis());
                    itemNo.incrementAndGet();
                    System.out.println("add item succeed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("add item failed");
                }
                Thread.sleep(threadLocalRandom.nextInt(200));
            }
        } catch (InterruptedException e) {

        }
    }
}
