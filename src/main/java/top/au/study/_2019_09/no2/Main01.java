package top.au.study._2019_09.no2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * description:
 * 试题编号：	201909-2
 * 试题名称：	小明种苹果（续）
 * 时间限制：	1.0s
 * 内存限制：	512.0MB
 *
 *
 * 得分 100满分
 *
 *
 * Time Complexity: O()
 * Space Complexity: O()
 * createTime: 2019-11-26 23:15
 * @author au
 */
public class Main01 {

    private int N;
    private int T;
    private int D;
    private int E;
    private ArrayList<Integer>[] a;
    /**
     * 用于标记每棵树是否有掉落的情况,
     * 默认值为 -1;
     * 有掉落的情况用 1表示;
     */
    private int[] isDrop;

    private void initData() {
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


    private void calculateT(ArrayList<Integer>[] a) {
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


    private void calculateD(ArrayList<Integer>[] a) {
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

    private void calculateE() {
        for (int i = 0; i < N; i++) {
            if (isDrop[i] == 1
                    && isDrop[(i+1)%N] == 1
                    && isDrop[(i+2)%N] == 1) {
                E++;
            }
        }
    }

    public Main01() {
        initData();
        calculateT(a);
        calculateD(a);
        calculateE();
    }

    public static void main(String[] args) {
        Main01 main = new Main01();
        System.out.print(main.T + " " + main.D + " " + main.E);
    }
}
