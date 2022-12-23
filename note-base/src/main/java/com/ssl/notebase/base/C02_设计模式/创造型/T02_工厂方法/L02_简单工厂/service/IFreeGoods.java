package com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.service;


import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.entity.AwardInfo;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.entity.ResponseResult;

/**
 * 免费商品发放接口
 * @author spikeCong
 * @date 2022/9/8
 **/
public interface IFreeGoods {

    /**
     * 简单工厂抽象出一个接口 = 发送奖品的行为
     * 定义一个公共请求参数和返回值
     */
    ResponseResult sendFreeGoods(AwardInfo awardInfo);

}
