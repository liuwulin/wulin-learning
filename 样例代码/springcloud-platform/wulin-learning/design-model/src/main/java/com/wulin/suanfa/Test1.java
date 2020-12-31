package com.wulin.suanfa;

/**
 * @author Liu WuLin
 * @date 2020 - 05 - 10 15:21
 */
public class Test1 {

    /**
     * 【程序1】  题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，
     * 小兔子长到第四个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
     * <p>
     * 1.程序分析：   兔子的规律为数列1,1,2,3,5,8,13,21….
     */
    public static void main(String args[]) {
        int i = 0;
        for (i = 1; i <= 20; i++) {
            System.out.println(f(i));
        }
    }

    public static int f(int x) {
        if (x == 1 || x == 2) {
            return 1;
        } else {
            return f(x - 1) + f(x - 2);
        }

    }
}
