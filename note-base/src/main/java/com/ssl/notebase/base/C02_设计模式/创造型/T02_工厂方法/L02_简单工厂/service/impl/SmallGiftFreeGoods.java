package com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.service.impl;

import com.alibaba.fastjson.JSON;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L01_原始方式.entity.SmallGiftInfo;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.entity.AwardInfo;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.entity.ResponseResult;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.service.IFreeGoods;


import java.util.UUID;

/**
 * 小礼品发放服务
 * @author spikeCong
 * @date 2022/9/8
 **/
public class SmallGiftFreeGoods implements IFreeGoods {

    @Override
    public ResponseResult sendFreeGoods(AwardInfo awardInfo) {

        SmallGiftInfo smallGiftInfo = new SmallGiftInfo();
        smallGiftInfo.setUserPhone(awardInfo.getExtMap().get("phone"));
        smallGiftInfo.setUserName(awardInfo.getExtMap().get("username"));
        smallGiftInfo.setAddress(awardInfo.getExtMap().get("address"));
        smallGiftInfo.setOrderId(UUID.randomUUID().toString());

        System.out.println("小礼品发放成,请注意查收: " + JSON.toJSON(smallGiftInfo));
        return new ResponseResult("200","小礼品发送成功",smallGiftInfo);
    }
}
