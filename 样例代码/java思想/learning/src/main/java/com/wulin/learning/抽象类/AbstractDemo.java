package com.wulin.learning.抽象类;

/**
 * @author 武林
 * @date 2022/6/15 17:53
 */
public class AbstractDemo {

    public static void main(String[] args) {

        fun(new Robot());

        fun(new Human());

    }

    public static void fun(Action act){
        act.commond(Action.EAT);
        act.commond(Action.SLEEP);
        act.commond(Action.WORK);
    }
}
