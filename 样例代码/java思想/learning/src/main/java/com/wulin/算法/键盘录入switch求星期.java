package com.wulin.算法;

import java.util.Scanner;

/**
 * @author liuwulin
 * @date 2024/1/22 20:43
 * <p>
 * 2、【键盘录入-switch-求星期】
 * 根据键盘录入的数值1、2、3、4，…7输出对应的星期一、星期二、星期三…星期日。
 */
public class 键盘录入switch求星期 {
    public static void main(String[] args) {
        System.out.print("请输入星期一、星期二、星期三、星期四。。。代表的数字:");
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        switch (num) {
            case 1:
                System.out.print("星期一");
                break;
            case 2:
                System.out.print("星期二");
                break;
            case 3:
                System.out.print("星期三");
                break;
            case 4:
                System.out.print("星期四");
                break;
            case 5:
                System.out.print("星期五");
                break;
            case 6:
                System.out.print("星期六");
                break;
            case 7:
                System.out.print("星期七");
                break;
            default:
                System.out.print("您输入的数字有误！");
        }
        input.close();
    }
}
