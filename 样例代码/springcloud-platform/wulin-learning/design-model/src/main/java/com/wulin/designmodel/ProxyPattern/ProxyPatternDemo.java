package com.wulin.designmodel.ProxyPattern;

/**
 * 代理模式
 */
public class ProxyPatternDemo {

    public static void main(String[] args) {
        Image image = new ProxyImage("test.png");

        //图像将从磁盘加载
        image.display();
        System.out.println("");

        //图像将无法从磁盘加载
        image.display();
    }

}
