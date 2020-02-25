package com.java.basis.threads;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicSafety {
    private static AtomicLong logicalclock = new AtomicLong();

    public static void main(String[] args)
    {

        for(int i=0;i<100;i++){
            Thread thread = new Thread(() -> {

                    for(int j=0;j<10000;j++)
                    {
//                        logicalclock.incrementAndGet();
                        logicalclock.getAndIncrement();
                    }

            });
//            thread.setDaemon(true);
            thread.start();
        }

        for(int i=0;i<10000;i++) {
            System.out.println(logicalclock.get());
        }
    }
}
