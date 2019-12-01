package top.au.study._2019_09.no5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * description:
 *
 * 试题编号：	201909-5
 * 试题名称：	城市规划
 * 时间限制：	3.0s
 * 内存限制：	512.0MB
 *
 * createTime: 2019-12-01 15:34
 * @author au
 */
public class Main {

    /**
     * 公交站的个数
     */
    private int N;
    /**
     * 重要站点的个数
     */
    private int M;
    /**
     * 需要选出的节点数
     */
    private int K;

    /**
     * 所有重要节点的编号
     */
    private int[] importantM;

    /**
     * 路径长度和的最小值
     */
    private int minPath = Integer.MAX_VALUE;

    /**
     * 两顶点之间的距离
     * eg. dis[i][j] 表示顶点 i 到顶点 j 之间的距离
     */
    private int[][] dis;

    /**
     * 用邻接表的方式表示
     * 索引代表节点
     * key代表相邻的点
     * value代表这两点间的权值
     * 节点编号从1开始
     */
    private HashMap<Integer,Integer>[] adj;

    private void initData() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        K = scanner.nextInt();

        importantM = new int[M];
        for (int i = 0; i < M; i++) {
            importantM[i] = scanner.nextInt();
        }

        adj = new HashMap[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new HashMap<>();
        }

        // todo 边界条件没有校验
        for (int i = 0; i < N - 1; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            adj[a].put(b, c);
            adj[b].put(a, c);

//            dis[a - 1][b - 1] = c;
//            dis[b - 1][a - 1] = c;
        }

        // 计算所有点之间的距离
        calculateAllDis();

        // 计算所有组合的可能
        calculate(importantM,K);
    }

    /**
     * 计算所有点之间的距离
     */
    private void calculateAllDis() {
        // 就用默认初始化的值，即 0
        dis = new int[N+1][N+1];
        for(int v = 0; v < N+1; v ++) {
            Arrays.fill(dis[v], -1);
        }

        for(int v = 1; v < N+1; v ++){
            dis[v][v] = 0;
            for(int w: adj(v)) {
                dis[v][w] = getWeight(v, w);
            }
        }

        for(int t = 1; t < N+1; t ++) {
            for(int v = 1; v < N+1; v ++) {
                for(int w = 1; w < N+1; w ++) {
                    if(dis[v][t] != Integer.MAX_VALUE
                            && dis[t][w] != Integer.MAX_VALUE
                            && dis[v][t] + dis[t][w] < dis[v][w]) {
                        dis[v][w] = dis[v][t] + dis[t][w];
                    }
                }
            }
        }
    }

    private int getWeight(int v, int w) {
        return adj[v].get(w);
    }



    private int[][] KFromMArr;
    private int[] MSubArr = new int[K];

    void C(int m,int k) {
        int i, j;
        for (i = k; i <= m; i++) {
            MSubArr[k - 1] = i - 1;
            if (k > 1) {
                C(i - 1, k - 1);
            } else {
                for (j = 0; j <= M - 1; j++) {
                    KFromMArr[i][j] = importantM[MSubArr[j]];
                    System.out.print(importantM[MSubArr[j]] + "  ");
                }
                System.out.println();
            }
        }
    }

    /**
     * todo 这个方法不对
     * @param originArr
     * @param k
     * @param res
     * @param tmpArr
     */
    static void generalCombinationCalculation(int[] originArr,
                                       int k,
                                       ArrayList<Integer>[] res,
                                       int[] tmpArr) {
        int i, j;
        for (i = k; i <= originArr.length; i++) {
            tmpArr[k - 1] = i - 1;
            if (k > 1) {
                generalCombinationCalculation(originArr,i - 1, res, tmpArr);
            } else {
                for (j = 0; j <= k - 1; j++) {
                    res[i].add(originArr[tmpArr[j]]);
                    System.out.print(originArr[tmpArr[j]] + "  ");
                }
                System.out.println();
            }
        }
    }

    /**
     * 从重要的顶点当中选出 K 个点
     */
    private void calculate(int[] importantM, int k) {
        // 从M个元素中挑选出k个元素的结果个数
        int resArrNum = 1;
        int tmpM = importantM.length;
        int tmpK = k-1;
        while (tmpM != k+1) {
            resArrNum *= tmpM;
            tmpM--;
        }
        while (tmpK != 1) {
            resArrNum /= tmpK;
            tmpK--;
        }

        KFromMArr = new int[resArrNum][k];
        MSubArr = new int[k];
//        HashMap<Integer, Integer>[] res = new HashMap[resArrNum];
//        for (int i = 0; i < resArrNum; i++) {
//            res[i] = new HashMap<>();
//
//        }
        C(importantM.length, k);
    }

    private void calculateMinPath() {
        for (int i = 0; i < KFromMArr.length; i++) {
            int tmpPath = 0;
            for (int j = 0; j+1 < KFromMArr[i].length; j++) {
                tmpPath += dis[KFromMArr[i][j]][KFromMArr[i][j+1]];
            }
            minPath = Math.min(minPath, tmpPath);
        }
    }

    /**
     * 获取 adj 直接相邻的顶点
     * @param v
     * @return
     */
    private Iterable<Integer> adj(int v) {
        return adj[v].keySet();
    }

    public static void main(String[] args) {
        int[] originArr = new int[]{1, 2, 3, 4, 5};
        ArrayList<Integer>[] res = new ArrayList[10];
        for (int i = 0; i < 10; i++) {
            res[i] = new ArrayList<>();
        }
        int k = 3;
        int[] tmpArr = new int[k];
        generalCombinationCalculation(originArr, k, res, tmpArr);

    }
}
