package com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L04_抽象工厂;


import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L04_抽象工厂.factory.AppliancesFactory;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L04_抽象工厂.factory.HisenseFactory;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L04_抽象工厂.product.AbstractFreezer;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L04_抽象工厂.product.AbstractTV;

/**
 * 客户端
 * 抽象工厂模式
 * 比工厂方法模式的抽象程度更高. 在工厂方法模式中每一个具体工厂只需要生产一种具体产品,但是在抽象工厂模式中一个具体工厂可以
 * 生产一组相关的具体产品,这样一组产品被称为产品族.产品族中的每一个产品都分属于某一个产品继承等级结构.
 * 优点
 * 1.对于不同产品系列有比较多共性特征时，可以使用抽象工厂模式，有助于提升组件的复用性.
 * 2.当需要提升代码的扩展性并降低维护成本时，把对象的创建和使用过程分开，能有效地将代码统一到一个级别上
 * 缺点
 * 1.增加新的产品等级结构麻烦,需要对原有结构进行较大的修改,甚至需要修改抽象层代码,这显然会带来较大不变,违背了开闭原则.
 **/
public class Client {

    private AbstractTV tv;

    private AbstractFreezer freezer;

    public Client(AppliancesFactory factory) {
        //在客户端看来就是使用抽象工厂来生产家电
        this.tv = factory.createTV();
        this.freezer = factory.createFreezer();
    }

    public AbstractTV getTv() {
        return tv;
    }

    public void setTv(AbstractTV tv) {
        this.tv = tv;
    }

    public AbstractFreezer getFreezer() {
        return freezer;
    }

    public void setFreezer(AbstractFreezer freezer) {
        this.freezer = freezer;
    }

    /**
     * 测试
     */
    public static void main(String[] args) {

        Client client = new Client(new HisenseFactory());
        AbstractTV tv = client.getTv();
        System.out.println(tv);

        AbstractFreezer freezer = client.getFreezer();
        System.out.println(freezer);
    }
}
