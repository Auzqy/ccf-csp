package top.au.study._2014_12.no1;

import java.util.HashMap;
import java.util.Scanner;

/**
 * description:
 * <p>
 * <p>
 * 试题编号：	201412-1
 * 试题名称：	门禁系统
 * 时间限制：	1.0s
 * 内存限制：	256.0MB
 * 问题描述：
 * 问题描述
 * 　　涛涛最近要负责图书馆的管理工作，需要记录下每天读者的到访情况。每位读者有一个编号，每条记录用读者的编号来表示。给出读者的来访记录，请问每一条记录中的读者是第几次出现。
 * 输入格式
 * 　　输入的第一行包含一个整数n，表示涛涛的记录条数。
 * 　　第二行包含n个整数，依次表示涛涛的记录中每位读者的编号。
 * 输出格式
 * 　　输出一行，包含n个整数，由空格分隔，依次表示每条记录中的读者编号是第几次出现。
 * 样例输入
 * 5
 * 1 2 1 1 3
 * 样例输出
 * 1 1 2 3 1
 * 评测用例规模与约定
 * 　　1≤n≤1,000，读者的编号为不超过n的正整数。
 * <p>
 * <p>
 *
 *     满分通过
 *
 * createTime: 2019-12-09 15:08
 *
 * @author au
 */
public class Main {

    /**
     * 总共的记录数
     */
    private int n;

    /**
     * 所有记录的信息
     */
    private int[] nums;

    private void initData() {
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();

        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
    }

    private void coreStatic() {
        StringBuilder res = new StringBuilder();

        HashMap<Integer, Integer> hashMap = new HashMap<>(n);

        for (int num : nums) {

            Integer orDefault = hashMap.getOrDefault(num, 0);
            int value = orDefault + 1;

            hashMap.put(num, value);
            res.append(value)
                    .append(" ");
        }

        System.out.print(res.toString().substring(0, res.toString().length() - 1));
    }

    public Main() {
        initData();
        coreStatic();
    }

    public static void main(String[] args) {
        new Main();
    }
}
