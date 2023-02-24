package com.ssl.note;

import com.ssl.note.entity.Order;
import com.ssl.note.service.IOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoteDatasourceApplicationTests {

    @Autowired
    private IOrderService orderService;

    @Test
    void getById() {
        Integer id = 1;
        Order order = orderService.getById(id);
        System.out.println(order);
    }
}
