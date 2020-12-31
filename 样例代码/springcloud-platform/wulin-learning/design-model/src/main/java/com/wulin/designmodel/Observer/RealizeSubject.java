package com.wulin.designmodel.Observer;

/**
 * @author Liu WuLin
 * @date 2020 - 05 - 07 10:29
 */
public class RealizeSubject extends AbstractSubject {

    @Override
    public void opreation() {
        System.out.println("update");
        notifyAllObservers();
    }
}