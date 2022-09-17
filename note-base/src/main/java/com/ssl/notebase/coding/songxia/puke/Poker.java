package com.ssl.notebase.coding.songxia.puke;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SongShengLin
 * @date 2022/9/17 11:11
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poker {

    /**
     * 扑克牌数字，不包含大小王
     * A 2 3 4 5 6 7 8 9 10 J Q K
     */
    private String num;

    /**
     * 花色：黑桃、红桃、方片、梅花
     */
    private String color;
}
