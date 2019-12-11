package top.au.study._2014_09.no4;

import java.util.*;

/**
 * 60	1.515s	180.3MB
 */
public class Main {

    /**
     * n: 格图的大小、
     * m: 栋栋的分店数量、
     * k: 客户的数量，
     * d: 不能经过的点的数量
     */
    private int n, m, k, d;

    /**
     * 所有分店的信息
     */
    private int[] shops;

    /**
     * 所有用户的信息
     */
    private int[] customers;

    private int minCost;


    /**
     * 四联通：从上面开始，顺时针转四个方向
     */
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    /**
     * 联通的节点默认用 0 表示，
     * 不联通的节点用 -1 表示
     * 分店： -3
     * 用户： c >= 1
     */
    private int[][] grid;

    /**
     * 用邻接表的形式存储建图后的内容
     */
    private HashSet<Integer>[] G;

    private boolean[] visited;
    private long[] dis;


    /**
     * 进行 bfs 遍历是使用的队列
     */
    private Queue<Integer> queue = new LinkedList<>();

    /**
     * 用于记录坐标不重复的用户数量
     */
    private int uniquePositionSize;

    private void initData() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        k = scanner.nextInt();
        d = scanner.nextInt();

        grid = new int[n][n];


        shops = new int[m];
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            shops[i] = convertX(y) * n + convertY(x);
        }

        HashSet<Integer> uniquePositionSet = new HashSet<>();
        customers = new int[k];
        for (int i = 0; i < k; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int c = scanner.nextInt();
            // 相同坐标的需要的订餐的数量进行累加
            grid[convertX(y)][convertY(x)] += c;
            customers[i] = convertX(y) * n + convertY(x);
            uniquePositionSet.add(customers[i]);
        }
        uniquePositionSize = uniquePositionSet.size();

        for (int i = 0; i < d; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            grid[convertX(y)][convertY(x)] = -1;
        }

        minCost = 0;
        G = constructGraph();
        visited = new boolean[G.length];
        dis = new long[G.length];
    }

    /**
     * 用邻接表的方式进行建图
     *
     * todo 去掉这个建图的过程
     *
     * @return 无向无权图
     */
    private HashSet<Integer>[] constructGraph() {
        HashSet<Integer>[] g = new HashSet[n * n];
        // 特别注意，new 对象的话，是不能够这样传递的
        // Arrays.fill(g, new HashSet<>());
        for (int i = 0; i < g.length; i++) {
            g[i] = new HashSet<>();
        }

        for (int v = 0; v < g.length; v++) {
            int x = v / n, y = v % n;
            if (grid[x][y] != -1) {
                for (int[] dir : dirs) {
                    int nextx = x + dir[0];
                    int nexty = y + dir[1];
                    if (inArea(nextx, nexty) && grid[nextx][nexty] != -1) {
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


    private void bfs() {

        for (int shop : shops) {
            /*
             *  对于同一个用户，如果有分店已经到达，说明该分店所需要的
             * 配送步数就一定为最少（小于等于其他分店，至于等于的情况，
             * 对于总结果是不影响的，所以以此做计算是没有问题的）
             */
            queue.add(shop);
            visited[shop] = true;
            dis[shop] = 0;
        }

        while (!queue.isEmpty()) {
            Integer v = queue.remove();
            int x = v / n, y = v % n;
            if (grid[x][y] != -1) {
                for (int[] dir : dirs) {
                    int nextx = x + dir[0];
                    int nexty = y + dir[1];
                    if (inArea(nextx, nextx) && grid[nextx][nexty] != -1) {
                        int next = nextx * n + nexty;
                        minCost += dis[next] * grid[nextx][nextx];
//                        g[v].add(next);
//                        g[next].add(v);
                        queue.add(next);
                    }
                }
            }

            for (Integer w : G[v]) {
                if (!visited[w]) {
                    visited[w] = true;
                    dis[w] = dis[v] + 1;

                    int wX = w / n, wY = w % n;
                    if (grid[wX][wY] > 0) {
                        minCost += dis[w] * grid[wX][wY];
                        // 所有用户的值都已经计算完成后，直接退出
                        if (--uniquePositionSize == 0) {
                            return;
                        }
                    }
                    queue.add(w);

                }
            }
        }
    }

    /**
     * 将输入的坐标转换为屏幕横坐标
     *
     * @param inputY
     * @return
     */
    private int convertX(int inputY) {
        return n - inputY;
    }

    /**
     * 将输入的坐标转换为屏幕纵坐标
     *
     * @param inputX
     * @return
     */
    private int convertY(int inputX) {
        return inputX - 1;
    }

    public Main() {
        initData();
        bfs();
        System.out.print(minCost);
    }

    public static void main(String[] args) {
        new Main();
    }
}