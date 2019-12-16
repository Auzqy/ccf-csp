package top.au.study._2014_09.no1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * description:  合并后测试提交效率
 * createTime: 2019-12-15 05:25
 * @author au
 */
public class Main01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        Arrays.sort(nums);
        int i = 0;
        int j = i + 1;

        int totalPair = 0;
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

        System.out.print(totalPair);
    }
}
