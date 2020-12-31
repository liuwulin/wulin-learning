package com.wulin.designmodel.Observer;

/**
 * @author Liu WuLin
 * @date 2020 - 05 - 07 10:29
 */
public interface Subject {
    void add(Observer observer);
    void del(Observer observer);
    void notifyAllObservers();
    void opreation();
}
