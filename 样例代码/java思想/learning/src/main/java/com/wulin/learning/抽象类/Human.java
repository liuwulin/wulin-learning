package com.wulin.learning.抽象类;

/**
 * @author 武林
 * @date 2022/6/15 17:45
 */
public class Human extends Action{
    @Override
    public void eat() {
        System.out.println("人吃饭");

    }

    @Override
    public void sleep() {
        System.out.println("人睡觉");

    }

    @Override
    public void work() {
        System.out.println("人工作");

    }
}
