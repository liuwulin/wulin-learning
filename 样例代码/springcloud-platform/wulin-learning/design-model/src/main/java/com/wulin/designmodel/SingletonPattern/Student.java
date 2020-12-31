package com.wulin.designmodel.SingletonPattern;

/**
 * @author Liu WuLin
 * @date 2020 - 04 - 17 18:36
 */

public class Student {
    public String name ;

    public int  score ;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
