package com.wulin.learning.抽象类;

/**
 * @author 武林
 * @date 2022/6/15 17:45
 */
public class Robot extends Action {
    @Override
    public void eat() {
        System.out.println("机器人充电");

    }

    @Override
    public void sleep() {

    }

    @Override
    public void work() {
        System.out.println("机器人工作");

    }
}
