package com.java.basis.concurrent.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockExample {
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static long valueLock = 0;

    public static void main(String[] args) {
        readLockExample();
        System.out.println(writerLockExample());
    }

    public static long writerLockExample() {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("begin A " + System.currentTimeMillis());
                reentrantReadWriteLock.writeLock().lock();
                System.out.println("begin A write lock:" + System.currentTimeMillis());
                for (int i = 0; i < 100; i++) {
                    valueLock++;
                }
                System.out.println("A action over" + System.currentTimeMillis());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantReadWriteLock.writeLock().unlock();
                System.out.println("A write unlock" + System.currentTimeMillis());
            }
        });
        threadA.start();

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("begin B " + System.currentTimeMillis());
                reentrantReadWriteLock.writeLock().lock();
                System.out.println("begin B write lock:" + System.currentTimeMillis());
                for (int i = 0; i < 100; i++) {
                    valueLock++;
                }
                System.out.println("B action over");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantReadWriteLock.writeLock().unlock();
                System.out.println("B write unlock" + System.currentTimeMillis());
            }
        });
        threadB.start();
        try {
            Thread.sleep(18000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return valueLock;
    }

    public static void readLockExample() {
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("begin c " + System.currentTimeMillis());
                reentrantReadWriteLock.readLock().lock();
                System.out.println("begin c read lock:" + System.currentTimeMillis());
                for (int i = 0; i < 100; i++) {
                    System.out.println("c:" + valueLock);
                }
                System.out.println("c action over");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantReadWriteLock.readLock().unlock();
                System.out.println("c read unlock" + System.currentTimeMillis());
            }
        });
        threadC.start();

        Thread threadD = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("begin d " + System.currentTimeMillis());
                reentrantReadWriteLock.readLock().lock();
                System.out.println("begin d read lock:" + System.currentTimeMillis());
                for (int i = 0; i < 100; i++) {
                    System.out.println("d:" + valueLock);
                }
                System.out.println("d action over");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantReadWriteLock.readLock().unlock();
                System.out.println("d read unlock" + System.currentTimeMillis());
            }
        });
        threadD.start();
    }
}
