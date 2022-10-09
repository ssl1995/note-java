package com.ssl.notebase.interview.demo1;


import java.util.Scanner;

/**
 * @author SongShengLin
 * @date 2022/8/27 16:13
 * @description
 */
public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scan.nextInt();
            }
        }
        rotate(matrix);
        rotate(matrix);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }

    }

    public static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length < 2) {
            return;
        }
        // 左上角和右下角坐标
        int tR = 0, tC = 0;
        int dR = matrix.length - 1, dC = matrix[0].length - 1;

        while (tR < dR) {
            rotateEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    private static void rotateEdge(int[][] matrix, int tR, int tC, int dR, int dC) {
        // 每次的交换次数
        int times = dR - tR;
        int temp = 0;
        for (int i = 0; i < times; i++) {
            // 每次暂存交换第一个数据
            temp = matrix[tR][tC + i];

            // 画图，定位四个点
            matrix[tR][tC + i] = matrix[dR - i][tC];
            matrix[dR - i][tC] = matrix[dR][dC - i];
            matrix[dR][dC - i] = matrix[tR + i][dC];
            matrix[tR + i][dC] = temp;
        }
    }

}
