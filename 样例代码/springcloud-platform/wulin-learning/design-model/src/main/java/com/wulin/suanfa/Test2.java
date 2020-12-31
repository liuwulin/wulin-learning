package com.wulin.suanfa;

/**
 * @author Liu WuLin
 * @date 2020 - 05 - 10 15:28
 */

/**
 * 【程序2】 题目：判断101-200之间有多少个素数，并输出所有素数。
 * <p>
 * 1.程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除，
 * <p>
 * 则表明此数不是素数，反之是素数。
 */
public class Test2 {

    public static void main(String[] args) {
        for (int i = 101; i <= 200; i++) {
//            if (i % 2 != 0) {
//                System.out.println(i);
//            }
            if(iszhishu(i)==true) {
                System.out.println(i);
            }

        }
    }

    public static boolean iszhishu(int x) {
        for (int i = 2; i <= x / 2; i++) {
            if (x % 2 == 0) {
                return false;
            }
        }
        return true;
    }
}
