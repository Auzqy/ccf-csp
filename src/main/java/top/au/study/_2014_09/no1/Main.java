package top.au.study._2014_09.no1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * description:
 *
 *
 * 试题编号：	201409-1
 * 试题名称：	相邻数对
 * 时间限制：	1.0s
 * 内存限制：	256.0MB
 * 问题描述：
 * 问题描述
 * 　　给定n个不同的整数，问这些数中有多少对整数，它们的值正好相差1。
 * 输入格式
 * 　　输入的第一行包含一个整数n，表示给定整数的个数。
 * 　　第二行包含所给定的n个整数。
 * 输出格式
 * 　　输出一个整数，表示值正好相差1的数对的个数。
 * 样例输入
 * 6
 * 10 2 6 3 7 8
 * 样例输出
 * 3
 * 样例说明
 * 　　值正好相差1的数对包括(2, 3), (6, 7), (7, 8)。
 * 评测用例规模与约定
 * 　　1<=n<=1000，给定的整数为不超过10000的非负整数。
 *
 * 满分通过
 *
 * createTime: 2019-12-08 09:29
 *
 * @author au
 */
public class Main {

    /**
     * 所有非负整数的个数
     */
    private int n;

    /**
     * 存储输入的数据
     */
    private int[] nums;

    private int totalPair;

    private void initData() {
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();

        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
    }


    private void findPair() {
        Arrays.sort(nums);
        int i = 0;
        int j = i + 1;

        /*
            由于数组中的元素为 n个不同的非负整数，
            故可做如下处理
         */
        while (i < n && j < n) {
            if (nums[j] - nums[i] == 1) {
                totalPair++;
            }
            i++;
            j++;
        }
    }

    public Main() {
        initData();
        findPair();
        System.out.print(totalPair);
    }

    public static void main(String[] args) {
        new Main();
    }
}
