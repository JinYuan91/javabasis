package com.java.basis.concurrent.tools;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    private static CountDownLatch countDownLatch=new CountDownLatch(3);

    public static void main(String[] args)
    {
        for(int i=0;i<2;i++)
        {
            AcquireCount acquireCount=new AcquireCount(countDownLatch);
            acquireCount.start();//countDownLath.await() //获取同步器资源
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0;i<3;i++)
        {
            ReleaseCount releaseCount=new ReleaseCount(countDownLatch);
            releaseCount.start();//countDownLatch.countDown() //释放同步器状态值
        }
    }
    static class ReleaseCount extends Thread
    {
        private CountDownLatch countDownLatch;
        public ReleaseCount(CountDownLatch countDownLatch)
        {
            this.countDownLatch=countDownLatch;
        }
        @Override
        public void run() {

            System.out.println(this.getId()+"-Release over");
            countDownLatch.countDown();//释放同步器状态 -1
        }
    }
    static class AcquireCount extends Thread
    {
        private CountDownLatch countDownLatch;
        public AcquireCount(CountDownLatch countDownLatch)
        {
            this.countDownLatch=countDownLatch;
        }
        @Override
        public void run() {
            System.out.println(this.getId()+"-Acquire begin");

                try {
                    countDownLatch.await();//等待获取锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getId()+"-Acquire over");
        }
    }
}
