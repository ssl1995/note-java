package com.note.ssl.note.redis.缓存击穿;

import lombok.Data;

/**
 * @Author: SongShengLin
 * @Date: 2023/01/02 23:06
 * @Describe:
 */
@Data
public class Product {

    private Long id;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品价格
     */
    private Integer price;
    /**
     * 产品详情
     */
    private String detail;

    public Product() {
    }

    public Product(Long id, String name, Integer price, String detail) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.detail = detail;
    }
}