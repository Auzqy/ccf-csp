package top.au.study._2014_03.no1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * description:
 *
 * 试题编号：	201403-1
 * 试题名称：	相反数
 * 时间限制：	1.0s
 * 内存限制：	256.0MB
 * 问题描述：
 * 问题描述
 * 　　有 N 个非零且各不相同的整数。请你编一个程序求出它们中有多少对相反数(a 和 -a 为一对相反数)。
 * 输入格式
 * 　　第一行包含一个正整数 N。(1 ≤ N ≤ 500)。
 * 　　第二行为 N 个用单个空格隔开的非零整数,每个数的绝对值不超过1000,保证这些整数各不相同。
 * 输出格式
 * 　　只输出一个整数,即这 N 个数中包含多少对相反数。
 * 样例输入
 * 5
 * 1 2 3 -1 -2
 * 样例输出
 * 2
 *
 * 满分通过
 *
 * createTime: 2019-12-07 22:54
 * @author au
 */
public class Main {

    /**
     * 所有正整数的个数
     */
    private int N;

    /**
     * 非零整数,
     * 每个数的绝对值不超过1000
     */
    private int[] intArr;

    /**
     * 所求数组中相反数的个数
     */
    private int resNum;

    private void initData() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();

        intArr = new int[N];
        for (int i = 0; i < N; i++) {
            intArr[i] = scanner.nextInt();
        }
    }

    /**
     * 整理数组为有序数组
     * 两个指针从数组头尾向中间移动
     */
    private void staticOppositeNum() {
        int i = 0;
        int j = N - 1;
        // 整理数组为有序数组
        Arrays.sort(intArr);
        while (i < j) {
            if (intArr[i] + intArr[j] > 0) {
                j--;
            }else if (intArr[i] + intArr[j] < 0) {
                i++;
            }else {
                resNum++;
                i++;
                j--;
            }
        }
    }

    public Main() {
        initData();
        staticOppositeNum();
        System.out.print(resNum);
    }

    public static void main(String[] args) {
        new Main();
    }
}
