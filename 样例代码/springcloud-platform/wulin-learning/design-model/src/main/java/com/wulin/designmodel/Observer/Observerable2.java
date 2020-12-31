package com.wulin.designmodel.Observer;

/**
 * @author Liu WuLin
 * @date 2020 - 05 - 07 10:28
 */
public class Observerable2 implements Observer {
    @Override
    public void update() {
        System.out.println("Observerable2 has received");
    }
}