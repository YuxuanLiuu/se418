package com.se418.consumerproducer;

import java.lang.management.MonitorInfo;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingDeque<Long> container = new LinkedBlockingDeque<>();
        AtomicInteger itemNo = new AtomicInteger();
        AtomicInteger successNo = new AtomicInteger();
        AtomicLong timeSum = new AtomicLong();

        Long timeout=100L;
        int threshold=20;


        Producer p1 = new Producer(container, itemNo);
        Producer p2 = new Producer(container, itemNo);
        Producer p3 = new Producer(container, itemNo);
        Consumer c1 = new Consumer(container, successNo, timeSum, threshold);
        Consumer c2 = new Consumer(container, successNo, timeSum, threshold);
        Consumer c3 = new Consumer(container, successNo, timeSum, threshold);
        TimeoutMonitor t = new TimeoutMonitor(container, timeout);

        ExecutorService service = Executors.newCachedThreadPool();



        service.execute(p1);
        service.execute(p2);
        service.execute(p3);
        service.execute(c1);
        service.execute(c2);
        service.execute(t);

        Thread.sleep( 20 * 1000);
        System.out.println("shutdowning");
        service.shutdownNow();
        System.out.println("shutdowned");
        System.out.println("number of items: " + itemNo +", Number of success item: " + successNo + ", time usage for all success item:" + timeSum);

    }
}
