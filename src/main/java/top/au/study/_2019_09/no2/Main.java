package top.au.study._2019_09.no2;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * description:
 * 试题编号：	201909-2
 * 试题名称：	小明种苹果（续）
 * 时间限制：	1.0s
 * 内存限制：	512.0MB
 *
 *
 * 得分 100满分（运行耗时 843ms）
 * （这一次是巧了，之后又运行了几次，均提示超时，得分为90分）
 *
 * createTime: 2019-11-26 23:15
 * @author au
 */
public class Main {

    private static int N;
    private static int T;
    private static int D;
    private static int E;
    private static ArrayList<Integer>[] a;
    /**
     * 用于标记每棵树是否有掉落的情况,
     * 默认值为 -1;
     * 有掉落的情况用 1表示;
     */
    private static int[] isDrop;

    /**
     * 初始化参数
     * Time Complexity: O(N*m)
     * Space Complexity: O(N*m)
     */
    private static void initData() {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        isDrop = new int[N];
        Arrays.fill(isDrop,-1);

        a = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            int m = scanner.nextInt();
            a[i] = new ArrayList<>();
            a[i].add(m);
            for (int j = 0; j < m; j++) {
                a[i].add(scanner.nextInt());
            }
        }
    }

    /**
     * 计算T
     * Time Complexity: O(N*m)
     * Space Complexity: O(1)
     */
    private static void calculateT(ArrayList<Integer>[] a) {
        for (int i = 0; i < N; i++) {
            int size = a[i].size();
            for (int j = size-1; j > 0; j--) {
                if (a[i].get(j) <= 0) {
                    T += a[i].get(j);
                } else {
                    T += a[i].get(j);
                    break;
                }
            }
        }
    }

    /**
     * 计算D
     * Time Complexity: O(N*m)
     * Space Complexity: O(1)
     */
    private static void calculateD(ArrayList<Integer>[] a) {
        for (int i = 0; i < N; i++) {
            int size = a[i].size();
            int tmpDrop = 0;
            int preTmpDrop = 0;
            int tmpDropJ = 0;

            for (int j = size-1; j > 0; j--) {
                if (a[i].get(j) > 0) {
                    tmpDrop = a[i].get(j);
                    tmpDropJ = j;
                    break;
                }
            }
            if (tmpDropJ == 1) {
                continue;
            }
            for (int j = tmpDropJ-1; j > 0; j--) {
                if (a[i].get(j) <= 0) {
                    preTmpDrop += a[i].get(j);
                } else {
                    preTmpDrop += a[i].get(j);
//                    if (preTmpDrop == tmpDrop) {
//                        break;
//                    } else if (preTmpDrop > tmpDrop) {
//                        isDrop[i] = 1;
//                    }

                    if (preTmpDrop > tmpDrop) {
                        isDrop[i] = 1;
                        D++;
                    }
                    break;
                }
            }
        }
    }


    /**
     * 计算T
     * Time Complexity: O(N*m)
     * Space Complexity: O(1)
     */
    private static void calculateTAndD(ArrayList<Integer>[] a) {
        for (int i = 0; i < N; i++) {
            int size = a[i].size();

            int tmpDrop = 0;
            int preTmpDrop = 0;
            int tmpDropJ = 0;

            for (int j = size-1; j > 0; j--) {
                if (a[i].get(j) <= 0) {
                    T += a[i].get(j);
                } else {
                    T += a[i].get(j);
                    tmpDrop = a[i].get(j);
                    tmpDropJ = j;
                    break;
                }
            }

            if (tmpDropJ == 1) {
                continue;
            }
            for (int j = tmpDropJ-1; j > 0; j--) {
                if (a[i].get(j) <= 0) {
                    preTmpDrop += a[i].get(j);
                } else {
                    preTmpDrop += a[i].get(j);

                    if (preTmpDrop > tmpDrop) {
                        isDrop[i] = 1;
                        D++;
                    }
                    break;
                }
            }
        }
    }

    /**
     * 计算E
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    private static void calculateE() {
        for (int i = 0; i < N; i++) {
            if (isDrop[i] == 1
                    && isDrop[(i+1)%N] == 1
                    && isDrop[(i+2)%N] == 1) {
                E++;
            }
        }
    }

    public static void main(String[] args) {
        initData();
//        calculateT(a);
//        calculateD(a);
        calculateTAndD(a);
        calculateE();


        System.out.print(T + " " + D + " " + E);
    }
}
