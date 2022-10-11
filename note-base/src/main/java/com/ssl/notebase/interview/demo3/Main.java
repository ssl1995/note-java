package com.ssl.notebase.interview.demo3;

import jodd.util.StringUtil;

import java.util.*;

/**
 * @author SongShengLin
 * @date 2022/10/11 20:20
 * @description
 */
public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list1.add(scan.nextLine());
        }
        List<String> list2 = new ArrayList<>();
        for (String s : list1) {
            if (!StringUtil.isBlank(s)) {
                list2.add(s);
            }
        }
        printIsOver(list2);
    }

    private static void printIsOver(List<String> list) {
        if (list == null) {
            return;
        }
        List<Node> nodes = new ArrayList<>();
        // int f( int x)
        for (String s : list) {
            Node node = new Node();
            String[] split1 = s.split("\\(");

            String[] split2 = split1[0].split(" ");
            node.methodType = split2[0];
            node.methodName = split2[1];

            String[] split3 = split1[1].split("\\)");
            if (split3.length==0) {
                node.params = "";
            } else {
                node.params = split3[0];
            }
            nodes.add(node);
        }
        Set<Node> sets = new HashSet<>();
        for (int i = 0; i < nodes.size(); i++) {
            if (sets.contains(nodes.get(i))) {
                System.out.println("No");
            } else {
                for (int j = i + 1; j < nodes.size(); j++) {
                    Node nodeI = nodes.get(i);
                    Node nodeJ = nodes.get(j);
                    if (!nodeI.methodName.equals(nodeJ.methodName)) {
                        System.out.println("Yes");
                    } else {
                        if (!nodeI.methodType.equals(nodeJ.methodType)) {
                            System.out.println("Yes");
                        } else {
                            // 方法名相同，类型也相同
                            String paramsI = nodeI.params;
                            String paramsJ = nodeJ.params;
                            if (paramsI.equals(paramsJ)) {
                                System.out.println("No");
                            } else {
                                System.out.println("Yes");
                            }
                        }
                    }
                    sets.add(nodeI);
                    sets.add(nodeJ);
                }
            }
        }
    }

    static class Node {
        public String methodType;
        public String methodName;

        public String params;

        public Node() {

        }
    }
}
