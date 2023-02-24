package com.ssl.note.db2;

import com.ssl.note.entity.Order;
import com.ssl.note.entity.Order2;
import com.ssl.note.service.db1.IOrderService;
import com.ssl.note.service.db2.IOrderService2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: SongShengLin
 * @Date: 2023/02/24 15:19
 * @Describe:
 */
@SpringBootTest
public class OrderDB2Test {

    @Autowired
    private IOrderService2 orderService2;

    @Test
    void selectDB2() {
        Integer id = 2;
        Order2 order = orderService2.getById(id);
        System.out.println(order);
    }
}
