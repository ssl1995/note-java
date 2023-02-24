package com.ssl.note.service.db1.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssl.note.entity.Order;
import com.ssl.note.mapper.OrderMapper;
import com.ssl.note.service.db1.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

}
