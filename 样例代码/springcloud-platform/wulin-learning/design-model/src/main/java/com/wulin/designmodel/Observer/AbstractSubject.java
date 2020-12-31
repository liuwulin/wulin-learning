package com.wulin.designmodel.Observer;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author Liu WuLin
 * @date 2020 - 05 - 07 10:29
 */
public abstract class AbstractSubject implements Subject{

    private Vector<Observer> mVector = new Vector<>();

    @Override
    public void add(Observer observer) {
        mVector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        mVector.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        Enumeration<Observer> enumeration = mVector.elements();
        while (enumeration.hasMoreElements()){
            Observer observer = enumeration.nextElement();
            observer.update();
        }
    }

    @Override
    public void opreation() {

    }
}