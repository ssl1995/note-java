package com.ssl.note.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ssl.note.entity.Order;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author songshenglin
 * @since 2023-01-22
 */
public interface IOrderService extends IService<Order> {
    void createOrder(Integer productId);
}
