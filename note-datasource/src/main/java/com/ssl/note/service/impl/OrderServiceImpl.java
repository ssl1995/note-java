package com.ssl.note.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssl.note.entity.Order;
import com.ssl.note.mapper.OrderMapper;
import com.ssl.note.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;


    @Override
    public void createOrder(Integer productId) {
        // 减库存

        // 模拟异常
        // 如果开启了分布式事务 + 断点到这里，发生了异常的话
        // undo_log表会提前存储好更新前的数据，便于回滚
//        int i = 1 / 0;

        // 新增一个订单
        Order order = new Order();
        order.setProductId(productId);
        order.setCount(1);
        orderMapper.insert(order);
    }
}
