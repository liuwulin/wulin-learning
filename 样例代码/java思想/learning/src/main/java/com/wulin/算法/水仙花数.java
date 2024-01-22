package com.wulin.算法;

/**
 * @author liuwulin
 * @date 2024/1/22 20:45
 * <p>
 * 请打印出所有的水仙花数。
 * <p>
 * 注：所谓的"水仙花数"是指一个三位数，其各位数字的立方和等于该数本身。例如153是一个"水仙花数"，因为153 = 1的三次方 + 5的三次方 + 3的三次方。（使用for循环遍历一下就出来了）
 */
public class 水仙花数 {
    public static void main(String[] args) {
        //打印所有的三位水仙花数 ≠ 判断一个数是否是水仙花数
        //与 JavaBasic01 打印所有的五位回文数相类似
        for (int Dig_1 = 0; Dig_1 < 10; Dig_1++)
            for (int Dig_2 = 0; Dig_2 < 10; Dig_2++)
                for (int Dig_3 = 1; Dig_3 < 10; Dig_3++)
                    if (Math.pow((double) Dig_1, 3) + Math.pow((double) Dig_2, 3) + Math.pow((double) Dig_3, 3) == Dig_1 + Dig_2 * 10 + Dig_3 * 100)
                        System.out.print(Dig_3 + "" + Dig_2 + "" + Dig_1 + "    ");
    }
}
