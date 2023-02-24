package com.ssl.note.service.db2.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssl.note.entity.Order2;
import com.ssl.note.mapper.db2.OrderMapper2;
import com.ssl.note.service.db2.IOrderService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: SongShengLin
 * @Date: 2023/02/24 15:08
 * @Describe:
 */
@Service
public class OrderServiceImpl2 extends ServiceImpl<OrderMapper2, Order2> implements IOrderService2 {

}
