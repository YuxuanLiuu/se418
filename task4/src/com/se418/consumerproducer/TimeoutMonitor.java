package com.se418.consumerproducer;

import java.util.concurrent.BlockingDeque;

public class TimeoutMonitor implements Runnable {
    private BlockingDeque<Long> container;
    private Long timeout;

    public TimeoutMonitor(BlockingDeque<Long> container, Long timeout) {
        this.container = container;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        System.out.println("start Timeout Monitor process");
        try {
            while (true) {
                Long now = System.currentTimeMillis();
                try {
                    Long inTime = container.getFirst();
                    if (now - inTime > timeout) {
                        System.out.println("item out for timeout.");
                        try {
                            container.takeFirst();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch(Exception e){
                    continue;
                }

                Thread.sleep(100);
            }
        } catch (InterruptedException e) {

        }
    }
}
