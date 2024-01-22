package com.wulin.算法;

import java.util.Scanner;

/**
 * 题目：
 * 古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子对数为多少？
 *
 * 分析：利用递归解决斐波那契数列。
 * 
 * @author liuwulin
 * @date 2024/1/22 20:31
 */
public class Demo01_Fibonacci {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);//Scanner类从键盘获取月份
        System.out.println("请输入月份：");
        int month = scanner.nextInt(); // 第几月
        System.out.println("该月的兔子的对数：" + fun(month));//方法调用
    }

    public static int fun(int a) {
        if (a == 1 || a == 2) {
            return 1;
        } else {
            return (fun(a - 1) + fun(a - 2));
        }
    }

}
