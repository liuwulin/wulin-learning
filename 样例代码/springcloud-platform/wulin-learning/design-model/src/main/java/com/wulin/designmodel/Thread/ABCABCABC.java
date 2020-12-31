package com.wulin.designmodel.Thread;

/**
 * 编写一个程序，启动三个线程，三个线程的ID分别是A，B，C；，每个线程将自己的ID值在屏幕上打印5遍，打印顺序是ABCABC...
 */
public class ABCABCABC {

    private int flag = 0;

    public synchronized void printA() {
        for(int i = 0; i < 5; i++) {
            while (flag != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            flag = 1;
            System.out.print("A");
            notifyAll();
        }
    }

    public synchronized void printB() {
        for(int i = 0; i < 5; i++) {
            while (flag != 1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            flag = 2;
            System.out.print("B");
            notifyAll();
        }
    }

    public synchronized void printC() {
        for(int i = 0; i < 5; i++) {
            while (flag != 2) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            flag = 0;
            System.out.print("C");
            notifyAll();
        }
    }

    public static void main(String[] args) {

        ABCABCABC abcabcabc = new ABCABCABC();

        new Thread(new Runnable() {
            @Override
            public void run() {
                abcabcabc.printA();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                abcabcabc.printB();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                abcabcabc.printC();
            }
        }).start();
    }
}
