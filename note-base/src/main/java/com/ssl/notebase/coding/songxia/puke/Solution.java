package com.ssl.notebase.coding.songxia.puke;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author SongShengLin
 * @date 2022/9/17 11:14
 * @description
 */
public class Solution {

    /**
     * 输入：扑克牌缺少两张，快速找出
     */
    private List<Poker> getMissPokers(List<Poker> pokers) {
        if (CollectionUtils.isEmpty(pokers)) {
            return new ArrayList<>();
        }
        // 1.按照花色分组,并且每组的数字升序排序
        Map<String, List<Poker>> colorPokers = pokers.stream()
                .sorted(Comparator.comparing(Poker::getNum))
                .collect(Collectors.groupingBy(Poker::getColor));

        // 2.每组不满13张的，找出缺失的
        List<Poker> missPokerLists = new ArrayList<>();
        for (List<Poker> subPokers : colorPokers.values()) {
            if (subPokers.size() != 13) {
                missPokerLists.addAll(getMissPoker(subPokers));
            }
        }
        // 3.打印出缺失的牌数
        missPokerLists.forEach(p -> System.out.println(p.getNum()));
        return missPokerLists;
    }

    private List<Poker> getMissPoker(List<Poker> subPokers) {
        List<String> allNums = getAllNums();
        Map<String, String> numAndColorMap = subPokers.stream().collect(Collectors.toMap(Poker::getNum, Poker::getColor, (o, n) -> n));
        Set<String> nums = numAndColorMap.keySet();
        allNums.removeAll(nums);
        // 不需要花色
        List<Poker> res = new ArrayList<>();
        for (String num : allNums) {
            Poker poker = new Poker();
            poker.setNum(num);
            res.add(poker);
        }
        return res;
    }

    private List<String> getAllNums() {
        List<String> allNum = new ArrayList<>();
        // A 2 3 4 5 6 7 8 9 10 J Q K
        allNum.add("A");
        allNum.add("2");
        allNum.add("3");
        allNum.add("4");
        allNum.add("5");
        allNum.add("6");
        allNum.add("7");
        allNum.add("8");
        allNum.add("9");
        allNum.add("10");
        allNum.add("J");
        allNum.add("Q");
        allNum.add("K");
        return allNum;
    }
}
