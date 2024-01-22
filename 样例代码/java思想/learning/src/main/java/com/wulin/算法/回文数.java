package com.wulin.算法;

import java.util.Scanner;

/**
 * @author liuwulin
 * @date 2024/1/22 20:36
 * <p>
 * 打印5位数中的所有回文数，每行打印10个数。最后统计一下一共有多少个5位回文数。
 * 注：回文数即正反读都是一个数，5位数的话，只要个位与万位相同，十位与千位相同就是一个回文数。
 */
public class 回文数 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        boolean flag = true;
        //或者采用num%10、num/10的循环方式，将其存入int型数组
        String s = Integer.toString(num); //将输入的正整数转换成字符串
        char arr[] = s.toCharArray();   //将字符串存入字符数组

        for (int i = 0; i < arr.length / 2; i++) {
            if (arr[i] != arr[arr.length - i - 1]) {  //数组中从左向右数第i个数对应从左向右数第arr.length - i - 1个数(从右向左为第i个数)
                flag = false;
                System.out.print("您输入的数不是回文数！");
                break;
            }
        }
        if (flag) System.out.print("您输入的数是回文数！");
        input.close();
    }
}
