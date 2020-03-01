package com.java.basis.concurrent.tools;

import java.util.concurrent.Exchanger;

public class ExchangerExample {
    private static Exchanger<Integer> integerExchanger = new Exchanger<>();

    public static void main(String[] args) {

        ExchangerThread exchangerThreadA=new ExchangerThread(integerExchanger,"A");
        ExchangerThread exchangerThreadB=new ExchangerThread(integerExchanger,"B",false);
        exchangerThreadA.start();
        exchangerThreadB.start();
    }

    static class ExchangerThread extends Thread {
        private boolean sort = true;//默认asc
        private Exchanger<Integer> integerExchanger;
        private String actionName;

        public ExchangerThread(Exchanger<Integer> integerExchanger, String actionName) {
            this.integerExchanger = integerExchanger;
            this.actionName = actionName;
        }

        public ExchangerThread(Exchanger<Integer> integerExchanger, String actionName, boolean sort) {
            this.integerExchanger = integerExchanger;
            this.actionName = actionName;
            this.sort = sort;
        }


        @Override
        public void run() {
            if (sort) {
                for (int i = 0; i < 100; i++) {
                    printResult(i);
                }
            } else {
                for (int i = 99; i >= 0; i--) {
                    printResult(i);
                }
            }

        }

        private void printResult(Integer exchangerValue) {
            System.out.println(actionName + "-exchanger before value:" + exchangerValue);
            try {
                Integer exchangerValueAfter = integerExchanger.exchange(exchangerValue);
                System.out.println(actionName + "-exchanger after value:" + exchangerValueAfter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

