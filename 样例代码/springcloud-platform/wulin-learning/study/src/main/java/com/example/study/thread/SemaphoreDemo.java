package com.example.study.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author 武林
 * @date 2020/8/18 17:58
 */
public class SemaphoreDemo implements Runnable {
    Semaphore semaphore = new Semaphore(3);   //创建三个信号量

    @Override
    public void run() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "得到一个信号量");
        try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();
        System.out.println(Thread.currentThread().getName() + "释放一个信号量");
    }

    public static void main(String[] args) {
        SemaphoreDemo sd = new SemaphoreDemo();
        ExecutorService service = Executors.newFixedThreadPool(6);
        while (true) {
            service.execute(sd);
        }
    }
}