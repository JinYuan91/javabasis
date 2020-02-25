package com.java.basis.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static long value = 0;

    public static void main(String[] args) {
//        System.out.println(getUnsafeSum());
        System.out.println(getSafeSum());
    }

    public static long getUnsafeSum() {
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        value++;
                    }
                }
            });
            thread.start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static long getSafeSum() {
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    reentrantLock.lock();
                    for (int j = 0; j < 10000; j++) {
                        value++;
                    }
                    reentrantLock.unlock();
                }
            });
            thread.start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }


}
