package com.wulin.designmodel.Observer;

/**
 * @author Liu WuLin
 * @date 2020 - 05 - 07 10:27
 */
public class Observerable1 implements Observer {
    @Override
    public void update() {
        System.out.println( "Observerable1 has received");
    }
}