package com.wulin.designmodel.Observer;

/**
 * @author Liu WuLin
 * @date 2020 - 05 - 07 10:30
 */
public class Main {

    public static void main(String[] args) {
        Subject sub = new RealizeSubject();
        sub.add(new Observerable1());
        sub.add(new Observerable2());
        sub.opreation();
    }
}
