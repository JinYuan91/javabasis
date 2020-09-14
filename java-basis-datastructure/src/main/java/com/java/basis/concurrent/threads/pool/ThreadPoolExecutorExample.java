package com.java.basis.concurrent.threads.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadPoolExecutorExample {
    private static final int COUNT_BITS = Integer.SIZE - 3;//29
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;//536870911  2^29 -1

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;//-536870912
    private static final int SHUTDOWN   =  0 << COUNT_BITS;//0
    private static final int STOP       =  1 << COUNT_BITS;//536870912
    private static final int TIDYING    =  2 << COUNT_BITS;//1073741824
    private static final int TERMINATED =  3 << COUNT_BITS;//1610612736

    public static void mian(String[] args) {
        ExecutorService fixedThreadPool= Executors.newFixedThreadPool(2);
    }


    static class RunnableExample implements Runnable {
        private String name;

        public RunnableExample(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(name +"-"+ i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    static class ThreadExample implements ThreadFactory
    {


        @Override
        public Thread newThread(Runnable r) {
            return new Thread(new RunnableExample("a"));
        }
    }
}
