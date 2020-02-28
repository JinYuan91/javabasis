package com.java.basis.concurrent.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {




    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new CyclicBarrierRunExample());
        Thread otherRunExampleA = new Thread(new OtherRunExample(cyclicBarrier, "A"));
        Thread otherRunExampleB = new Thread(new OtherRunExample(cyclicBarrier, "B"));
        otherRunExampleA.start();
        otherRunExampleB.start();
    }

    static class CyclicBarrierRunExample implements Runnable {

        @Override
        public void run() {
            System.out.println("CyclicBarrierRun Example");
        }
    }

    static class OtherRunExample implements Runnable {
        private CyclicBarrier cyclicBarrier;
        private String name;

        public OtherRunExample(CyclicBarrier cyclicBarrier, String name) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + " begin run input barrier");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(name + "go on run");

        }
    }
}
