package top.au.study._2013_12.no1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * description:
 *
 * 试题编号：	201312-1
 * 试题名称：	出现次数最多的数
 * 时间限制：	1.0s
 * 内存限制：	256.0MB
 * 问题描述：
 * 问题描述
 * 　　给定n个正整数，找出它们中出现次数最多的数。如果这样的数有多个，请输出其中最小的一个。
 * 输入格式
 * 　　输入的第一行只有一个正整数n(1 ≤ n ≤ 1000)，表示数字的个数。
 * 　　输入的第二行有n个整数s1, s2, …, sn (1 ≤ si ≤ 10000, 1 ≤ i ≤ n)。相邻的数用空格分隔。
 * 输出格式
 * 　　输出这n个次数中出现次数最多的数。如果这样的数有多个，输出其中最小的一个。
 * 样例输入
 * 6
 * 10 1 10 20 30 20
 * 样例输出
 * 10
 *
 * 满分通过
 *
 * createTime: 2019-12-07 10:50
 *
 * @author au
 */
public class Main {

    private int n;
    private int[] s;
    private HashMap<Integer, Integer> numAndCount;

    private void initData() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        s = new int[n];
        numAndCount = new HashMap<>();
        for (int i = 0; i < n; i++) {
            s[i] = scanner.nextInt();
            int orDefault = numAndCount.getOrDefault(s[i], 0);
            numAndCount.put(s[i], orDefault+1);
        }
    }

    private void findMaxCountAndMinNum(HashMap<Integer, Integer> numAndCount) {
        // 用于记录出现最多的次数
        int maxCount = -1;
        // 用于记录出现次数最多的元素对应的数字
        int minNumAndMaxCount = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : numAndCount.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (value > maxCount) {
                maxCount = value;
                minNumAndMaxCount = key;
            } else if (value == maxCount) {
                if (key < minNumAndMaxCount) {
                    minNumAndMaxCount = key;
                }
            }
        }
        System.out.print(minNumAndMaxCount);
    }


    public Main() {
        initData();
        findMaxCountAndMinNum(numAndCount);
    }

    public static void main(String[] args) {
        new Main();
    }
}
