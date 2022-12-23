package com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L01_原始方式.service;

import com.alibaba.fastjson.JSON;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L01_原始方式.entity.SmallGiftInfo;

/**
 * 小礼品服务
 * @author spikeCong
 * @date 2022/9/8
 **/
public class SmallGiftService {

    public Boolean giveSmallGift(SmallGiftInfo smallGiftInfo){

        System.out.println("小礼品已发送,获奖用户注意查收! " + JSON.toJSON(smallGiftInfo));
        return true;
    }
}
