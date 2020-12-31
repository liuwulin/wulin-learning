package com.wulin.lambdastream;

import lombok.Getter;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Liu WuLin
 * @date 2020 - 03 - 23 15:33
 */
public class ThreadLocalTest {

    //创建两个ThreadLocal对象
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalTest test = new ThreadLocalTest();
        ExecutorService executors= Executors.newFixedThreadPool(2);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                test.longLocal.set(Thread.currentThread().getId());
                test.stringLocal.set(Thread.currentThread().getName());
                System.out.println(test.longLocal.get());
                System.out.println(test.stringLocal.get());
            }
        });
        executors.execute(new Runnable() {
            @Override
            public void run() {
                test.longLocal.set(Thread.currentThread().getId());
                test.stringLocal.set(Thread.currentThread().getName());
                System.out.println(test.longLocal.get());
                System.out.println(test.stringLocal.get());
            }
        });

        Thread.currentThread();


    }

}
