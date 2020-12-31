package com.wulin.designmodel.Thread;

/**
 * 要求线程a执行完才开始线程b, 线程b执行完才开始线程
 * <p>
 * join()解释：https://blog.csdn.net/qq_18505715/article/details/79795728
 * <p>
 * wait()  和  notify()  解释：https://blog.csdn.net/chaozhi_guo/article/details/50249177
 * <p>
 * join()的作用：主要作用是同步，它可以使得线程之间的并行执行变为串行执行。在A线程中调用了B线程的join()方法时，表示只有当B线程执行完毕时，A线程才能继续执行。
 * <p>
 * public void joinDemo(){
 * //....
 * Thread t=new Thread(payService);
 * t.start();
 * //....
 * //其他业务逻辑处理,不需要确定t线程是否执行完
 * insertData();
 * //后续的处理，需要依赖t线程的执行结果，可以在这里调用join方法等待t线程执行结束
 * t.join();
 * }
 */
public class T1T2T3 {

    public static class PrintThread extends Thread {

        PrintThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(getName() + " : " + i);
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        PrintThread t1 = new PrintThread("t1");
        PrintThread t2 = new PrintThread("t2");
        PrintThread t3 = new PrintThread("t3");

        t1.run();
        t1.join();
        t2.run();
        t2.join();
        t3.run();
        t3.join();
    }


}
