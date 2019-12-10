package top.au.study._2014_09.no4;

import java.util.*;

/**
 * description:
 *
 * 试题编号：	201409-4
 * 试题名称：	最优配餐
 * 时间限制：	1.0s
 * 内存限制：	256.0MB
 * 问题描述：
 * 问题描述
 * 　　栋栋最近开了一家餐饮连锁店，提供外卖服务。随着连锁店越来越多，怎么合理的给客户送餐成为了一个急需解决的问题。
 * 　　栋栋的连锁店所在的区域可以看成是一个n×n的方格图（如下图所示），方格的格点上的位置上可能包含栋栋的分店（绿色标注）或者客户（蓝色标注），有一些格点是不能经过的（红色标注）。
 * 　　方格图中的线表示可以行走的道路，相邻两个格点的距离为1。栋栋要送餐必须走可以行走的道路，而且不能经过红色标注的点。
 *
 *
 * 　　送餐的主要成本体现在路上所花的时间，每一份餐每走一个单位的距离需要花费1块钱。每个客户的需求都可以由栋栋的任意分店配送，每个分店没有配送总量的限制。
 * 　　现在你得到了栋栋的客户的需求，请问在最优的送餐方式下，送这些餐需要花费多大的成本。
 * 输入格式
 * 　　输入的第一行包含四个整数n, m, k, d，分别表示方格图的大小、栋栋的分店数量、客户的数量，以及不能经过的点的数量。
 * 　　接下来m行，每行两个整数xi, yi，表示栋栋的一个分店在方格图中的横坐标和纵坐标。
 * 　　接下来k行，每行三个整数xi, yi, ci，分别表示每个客户在方格图中的横坐标、纵坐标和订餐的量。（注意，可能有多个客户在方格图中的同一个位置）
 * 　　接下来d行，每行两个整数，分别表示每个不能经过的点的横坐标和纵坐标。
 * 输出格式
 * 　　输出一个整数，表示最优送餐方式下所需要花费的成本。
 * 样例输入
 * 10 2 3 3
 * 1 1
 * 8 8
 * 1 5 1
 * 2 3 3
 * 6 7 2
 * 1 2
 * 2 2
 * 6 8
 * 样例输出
 * 29
 * 评测用例规模与约定
 * 　　前30%的评测用例满足：1<=n <=20。
 * 　　前60%的评测用例满足：1<=n<=100。
 * 　　所有评测用例都满足：1<=n<=1000，1<=m, k, d<=n^2。可能有多个客户在同一个格点上。每个客户的订餐量不超过1000，每个客户所需要的餐都能被送到。
 *
 * 得分60 1.484s	261.8MB
 *
 * createTime: 2019-12-09 12:04
 * @author au
 */
public class Main_v1 {

    /**
     * n: 格图的大小、
     * m: 栋栋的分店数量、
     * k: 客户的数量，
     * d: 不能经过的点的数量
     */
    private int n,m,k, d;

//    /**
//     * 所有目标坐标位置，即用户
//     * 索引表示坐标，
//     * 值为送一份餐的最优的花费成本
//     */
//    private int[][] targets;

    /**
     * 索引表示图的节点编号，
     * 值为到达该点的最短距离（送一份餐的最优的花费成本）
     */
    private int[] minDis;

    /**
     * 所有分店的信息
     */
    private Shop[] shops;

    /**
     * 所有用户的信息
     */
    private Customer[] customers;

    private int minCost;

//    /**
//     * 每个不能经过的点
//     * 索引表示坐标，
//     * 值为-1表示此路不通
//     */
//    private int[][] ds;

    class Shop {
        /**
         *  (x,y) 该分店的坐标位置
         */
        private int x;
        private int y;

        public Shop(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    class Customer {
        /**
         *  (x,y) 该用户的坐标位置
         */
        private int x;
        private int y;
        /**
         * 该用户订餐量
         */
        private int c;

        public Customer(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }



    /**
     * 四联通：从上面开始，顺时针转四个方向
     */
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    /**
     * 联通的节点默认用 0 表示，
     * 不联通的节点用 -1 表示
     */
    private int[][] grid;

    /**
     * 用邻接表的形式存储建图后的内容
     */
    private HashSet<Integer>[] G;

    private boolean[] visited;

    /**
     * 键代表起始点，
     * 值代表从该起始点到达各点的距离
     */
    private HashMap<Integer, Integer[]> disMap;

    private void initData() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        k = scanner.nextInt();
        d = scanner.nextInt();

        grid = new int[n][n];

        shops = new Shop[m];
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            shops[i] = new Shop(convertX(y), convertY(x));
        }

        customers = new Customer[k];
        for (int i = 0; i < k; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int c = scanner.nextInt();
            customers[i] = new Customer(convertX(y), convertY(x), c);
        }

        disMap = new HashMap<>();


//        ds = new int[n][n];
        for (int i = 0; i < d; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
//            ds[x][y] = -1;
            grid[convertX(y)][convertY(x)] = -1;
        }

        minCost = 0;
//        targets = new int[n][n];

//        buildGrid();
        G = constructGraph();
        visited = new boolean[G.length];

    }

    /**
     * 用邻接表的方式进行建图
     * @return  无向无权图
     */
    private HashSet<Integer>[] constructGraph() {
        HashSet<Integer>[] g = new HashSet[n * n];
        // 特别注意，new 对象的话，是不能够这样传递的
//        Arrays.fill(g, new HashSet<>());
        for (int i = 0; i < g.length; i++) {
            g[i] = new HashSet<>();
        }

        for (int v = 0; v < g.length; v++) {
            int x = v / n, y = v % n;
            if (grid[x][y] == 0) {
                for (int[] dir : dirs) {
                    int nextx = x + dir[0];
                    int nexty = y + dir[1];
                    if (inArea(nextx, nexty) && grid[nextx][nexty] == 0) {
                        int next = nextx * n + nexty;
                        g[v].add(next);
                        g[next].add(v);
                    }
                }
            }
        }

        return g;
    }


    private boolean inArea(int nextx, int nexty) {
        return nextx >= 0 && nextx < n && nexty >= 0 && nexty < n;
    }

    private void USSSPath(Integer start, Integer[] dis2AllFromStart) {

//        visited = new boolean[G.length];
//        dis = new int[G.length];
//
//        for (int i = 0; i < G.length; i++) {
//            dis[i] = -1;
//        }

        bfs(start,dis2AllFromStart);
    }

    private void bfs(Integer start, Integer[] dis2AllFromStart) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
//        dis[start] = 0;
        dis2AllFromStart[start] = 0;

        while (!queue.isEmpty()) {
            Integer v = queue.remove();
            for (Integer w : G[v]) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
//                    dis[w] = dis[v] + 1;
                    dis2AllFromStart[w] = dis2AllFromStart[v] + 1;
                }
            }
        }
    }

    private void calculateAllShopDis() {
        for (Shop shop : shops) {
            int start = shop.x * n + shop.y;
            Integer[] dis2AllFromStart = new Integer[G.length];
            Arrays.fill(dis2AllFromStart, -1);
            resetVisitedFalse();
            bfs(start, dis2AllFromStart);
            disMap.put(start, dis2AllFromStart);
        }
    }

    private void resetVisitedFalse() {
        Arrays.fill(visited, false);
    }

    private void calculateAllCustomerCost() {
        for (Customer customer : customers) {
            int v = customer.x * n + customer.y;
            int minDis = Integer.MAX_VALUE;
            for (Map.Entry<Integer, Integer[]> entry : disMap.entrySet()) {
                Integer[] dis = entry.getValue();
                if (minDis > dis[v] && dis[v] > 0) {
                    minDis = dis[v];
                }
            }
            minCost += minDis * customer.c;
        }
    }

    /**
     * 将输入的坐标转换为屏幕横坐标
     * @param inputY
     * @return
     */
    private int convertX(int inputY) {
        return n - inputY;
    }

    /**
     * 将输入的坐标转换为屏幕纵坐标
     * @param inputX
     * @return
     */
    private int convertY(int inputX) {
        return inputX - 1;
    }

    public Main_v1() {
        initData();
        calculateAllShopDis();
        calculateAllCustomerCost();
        System.out.print(minCost);
    }


    public static void main(String[] args) {
        new Main_v1();
    }
}
