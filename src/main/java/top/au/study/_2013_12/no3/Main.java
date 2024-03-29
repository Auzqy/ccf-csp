package top.au.study._2013_12.no3;

import java.util.Scanner;

/**
 * description:
 *
 * 试题编号：	201312-3
 * 试题名称：	最大的矩形
 * 时间限制：	1.0s
 * 内存限制：	256.0MB
 * 问题描述：
 * 问题描述
 * 　　在横轴上放了n个相邻的矩形，每个矩形的宽度是1，而第i（1 ≤ i ≤ n）个矩形的高度是hi。这n个矩形构成了一个直方图。例如，下图中六个矩形的高度就分别是3, 1, 6, 5, 2, 3。
 *
 *
 *
 * 　　请找出能放在给定直方图里面积最大的矩形，它的边要与坐标轴平行。对于上面给出的例子，最大矩形如下图所示的阴影部分，面积是10。
 *
 * 输入格式
 * 　　第一行包含一个整数n，即矩形的数量(1 ≤ n ≤ 1000)。
 * 　　第二行包含n 个整数h1, h2, … , hn，相邻的数之间由空格分隔。(1 ≤ hi ≤ 10000)。hi是第i个矩形的高度。
 * 输出格式
 * 　　输出一行，包含一个整数，即给定直方图内的最大矩形的面积。
 * 样例输入
 * 6
 * 3 1 6 5 2 3
 * 样例输出
 * 10
 *
 * 满分通过
 *
 * createTime: 2019-12-07 12:22
 * @author au
 */
public class Main {

    /**
     * 矩形的数量(1 ≤ n ≤ 1000)
     */
    private int n;

    /**
     * hi是第i个矩形的高度。
     * (1 ≤ hi ≤ 10000)。
     */
    private int[] h;

    /**
     * 用于记录最大的面积的值
     */
    private long maxArea = -1;

    private void initData() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = scanner.nextInt();
        }
    }

    private void calculateArea() {
        for (int i = 0; i < n; i++) {
            int curArea = h[i];

            // 以 i 为最低点向左扩展
            int j = i-1;
            while (j>=0 && h[i]<=h[j]) {
                curArea += h[i];
                j--;
            }

            // 以 i 为最低点向右扩展
            j = i + 1;
            while (j<n && h[i]<=h[j]) {
                curArea += h[i];
                j++;
            }
            // 尝试更新面积最大值
            if (curArea > maxArea) {
                maxArea = curArea;
            }
        }
    }

    public Main() {
        initData();
        calculateArea();
        System.out.print(maxArea);
    }

    public static void main(String[] args) {
        new Main();
    }
}
