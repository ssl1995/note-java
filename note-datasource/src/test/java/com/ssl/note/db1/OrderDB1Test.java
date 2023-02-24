package com.ssl.note.db1;

import com.ssl.note.entity.Order;
import com.ssl.note.service.db1.IOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: SongShengLin
 * @Date: 2023/02/24 15:19
 * @Describe:
 */
@SpringBootTest
public class OrderDB1Test {

    @Autowired
    private IOrderService orderService;

    @Test
    void selectDB1() {
        Integer id = 1;
        Order order = orderService.getById(id);
        System.out.println(order);
    }
}
