package com.java.basis.concurrent.tools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    private static Semaphore semaphore = new Semaphore(2);
    private static CountDownLatch countDownLatch=new CountDownLatch(20);

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            ActionExample actionExample = new ActionExample(semaphore,countDownLatch);
            actionExample.start();

        }
        try {
            countDownLatch.await();
            System.out.println("action over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ActionExample extends Thread {
        private Semaphore semaphore;
        private CountDownLatch countDownLatch;

        public ActionExample(Semaphore semaphore,CountDownLatch countDownLatch) {
            this.semaphore = semaphore;
            this.countDownLatch=countDownLatch;
        }

        @Override
        public void run() {

            System.out.println(getId() + "-thread action  get semaphore");
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getId() + "-thread get semaphore sucess");
            System.out.println(getId() + "-thread do something complete");

            semaphore.release();
            System.out.println(getId() + "-thread release semaphore");
            countDownLatch.countDown();

        }
    }

}
