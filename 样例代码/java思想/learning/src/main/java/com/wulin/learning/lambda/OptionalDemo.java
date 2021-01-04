package com.wulin.learning.lambda;


import java.util.Optional;

public class OptionalDemo {

    public static void main(String[] args) {


    }

    public Integer aa() {
        //常规写法
        String s = "sss";
        if (s == null) {
            return 0;
        } else {
            return s.length();
        }
        //Java 8 写法：
//        return Optional.ofNullable(s).orElse("").length();
    }
}
