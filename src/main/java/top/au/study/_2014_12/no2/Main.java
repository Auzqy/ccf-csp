package top.au.study._2014_12.no2;

import javax.swing.*;
import java.util.Scanner;

/**
 * description:
 * <p>
 * 试题编号：	201412-2
 * 试题名称：	Z字形扫描
 * 时间限制：	2.0s
 * 内存限制：	256.0MB
 * 问题描述：
 * 问题描述
 * 　　在图像编码的算法中，需要将一个给定的方形矩阵进行Z字形扫描(Zigzag Scan)。给定一个n×n的矩阵，Z字形扫描的过程如下图所示：
 * <p>
 * 　　对于下面的4×4的矩阵，
 * 　　1 5 3 9
 * 　　3 7 5 6
 * 　　9 4 6 4
 * 　　7 3 1 3
 * 　　对其进行Z字形扫描后得到长度为16的序列：
 * 　　1 5 3 9 7 3 9 5 4 7 3 6 6 4 1 3
 * 　　请实现一个Z字形扫描的程序，给定一个n×n的矩阵，输出对这个矩阵进行Z字形扫描的结果。
 * 输入格式
 * 　　输入的第一行包含一个整数n，表示矩阵的大小。
 * 　　输入的第二行到第n+1行每行包含n个正整数，由空格分隔，表示给定的矩阵。
 * 输出格式
 * 　　输出一行，包含n×n个整数，由空格分隔，表示输入的矩阵经过Z字形扫描后的结果。
 * 样例输入
 * 4
 * 1 5 3 9
 * 3 7 5 6
 * 9 4 6 4
 * 7 3 1 3
 * 样例输出
 * 1 5 3 9 7 3 9 5 4 7 3 6 6 4 1 3
 * 评测用例规模与约定
 * 　　1≤n≤500，矩阵元素为不超过1000的正整数。
 *
 * 满分通过
 *
 * createTime: 2019-12-09 15:28
 *
 * @author au
 */
public class Main {


    private int n;

    /**
     * 用于存储原始矩阵
     */
    private int[][] matrix;

    /**
     * 屏幕坐标系
     * 向右，
     * 向左下，
     * 向下，
     * 向右上
     */
    private int[][] direct = {{1, 0}, {-1, 1}, {0, 1}, {1, -1}};

    private void initData() {
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();

        matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 注意这里的赋值为了使i对应x轴，使j对应y轴
                matrix[j][i] = scanner.nextInt();
            }
        }
    }

    private String readMatrix() {
        StringBuilder res = new StringBuilder();

        int total = n * n;

        int i = 0;
        int j = 0;
        res.append(matrix[i][j])
            .append(" ");
        while (total > 1) {

            if (hasRight(i)) {
                i += direct[0][0];
                j += direct[0][1];
                res.append(matrix[i][j])
                        .append(" ");
                total--;
            }

            while (hasLeftDown(i, j)) {
                i += direct[1][0];
                j += direct[1][1];
                res.append(matrix[i][j])
                        .append(" ");
                total--;
                // 边界条件
                if (j == n - 1 && hasRight(i)) {
                    i += direct[0][0];
                    j += direct[0][1];
                    res.append(matrix[i][j])
                            .append(" ");
                    total--;
                }
            }

            if (hasDown(j)) {
                i += direct[2][0];
                j += direct[2][1];
                res.append(matrix[i][j])
                        .append(" ");
                total--;
            }

            while (hasRightUp(i, j)) {
                i += direct[3][0];
                j += direct[3][1];
                res.append(matrix[i][j])
                        .append(" ");
                total--;

                // 边界条件
                if (i == n - 1 && hasDown(j)) {
                    i += direct[2][0];
                    j += direct[2][1];
                    res.append(matrix[i][j])
                            .append(" ");
                    total--;
                }
            }

        }

        return res.toString().substring(0, res.toString().length() - 1);
    }

    /**
     * 继续向右上方向是否合法
     *
     * @param i
     * @param j
     * @return
     */
    private boolean hasRightUp(int i, int j) {
        i += direct[3][0];
        j += direct[3][1];
        return i < n && j >= 0;
    }

    /**
     * 继续向下方向是否合法
     *
     * @param j
     * @return
     */
    private boolean hasDown(int j) {
        j += direct[2][1];
        return j < n;
    }

    /**
     * 继续向左下方向是否合法
     *
     * @param i
     * @param j
     * @return
     */
    private boolean hasLeftDown(int i, int j) {
        i += direct[1][0];
        j += direct[1][1];
        return i >= 0 && j < n;
    }

    /**
     * 继续向右是否合法
     *
     * @param i
     * @return
     */
    private boolean hasRight(int i) {
        i += direct[0][0];
        return i < n;
    }

    public Main() {
        initData();
        System.out.print(readMatrix());
    }

    public static void main(String[] args) {
        new Main();
    }
}
