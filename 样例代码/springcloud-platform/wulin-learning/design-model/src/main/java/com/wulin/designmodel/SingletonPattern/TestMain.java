package com.wulin.designmodel.SingletonPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liu WuLin
 * @date 2020 - 04 - 17 15:58
 */
public class TestMain {
    public static List<Student> list = new ArrayList();
    static {

        list.add(new Student("战三",90));
        list.add(new Student("李四",80));
    }

    public static void main(String[] args) {
        Singleton.INSTANC.aaa();
        System.out.println(list.size());


    }
}
