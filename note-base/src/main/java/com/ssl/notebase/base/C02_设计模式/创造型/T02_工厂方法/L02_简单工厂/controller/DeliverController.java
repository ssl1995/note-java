package com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.controller;


import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.entity.AwardInfo;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.entity.ResponseResult;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.factory.FreeGoodsFactory;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.service.IFreeGoods;

/**
 * 使用简单工厂模式
 * 具体工厂 + 抽象产品 + 具体产品
 * 简单工厂不算23种设计模式，只是一个编程习惯。
 * 适用场景
 * 1. 需要创建的对象较少
 * 2. 客户端不关心对象的创建
 * 优点
 * 1.封装了创建对象过程，将客户端与服务端业务逻辑解耦，更加容易扩展
 * 缺点
 * 1.新增新产品时候，还是需要修改工厂类的代码，违背了开闭原则
 **/
public class DeliverController {

    //发放奖品
    public ResponseResult awardToUser(AwardInfo awardInfo) {
        try {
            IFreeGoods freeGoods = FreeGoodsFactory.getInstance(awardInfo.getAwardTypes());
            ResponseResult responseResult = freeGoods.sendFreeGoods(awardInfo);
            return responseResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult("201", "奖品发放失败!");
        }
    }

}
