package com.wulin.lambdastream;

/**
 * @author Liu WuLin
 * @date 2020 - 03 - 23 15:11
 */
public class MultiThreadDemo {


    public static class Number {
        private    int value = 0;

        public   void increase() throws InterruptedException {
            //这个变量对于该线程属于局部变量
            value = 10;
            Thread.sleep(10);
            System.out.println("increase value: " + value);
        }

        public    void decrease() throws InterruptedException {
            //同样这个变量对于该线程属于局部变量
            System.out.println("increase value: " + value);
            value = -10;
            Thread.sleep(10);
            System.out.println("decrease value: " + value);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Number number = new Number();
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    number.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    number.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        a.start();
        b.start();
    }
}
