package top.au.study._2014_09.no4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 80	运行超时	183.0MB
 */
public class Main_v4 {
    static class Vertex{
        public int x;
        int y;
        int step;

        Vertex(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;

        }

        Vertex() {

        }
    }

    public static void main(String[] args) {

        long[][] map = new long[1001][1001];
        Queue<Vertex> q = new LinkedList<>();
        boolean[][] vis = new boolean[1001][1001];
        int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        Scanner in = new Scanner(System.in);
        long size = in.nextLong();
        long m = in.nextLong();
        long k = in.nextLong();
        long d = in.nextLong();
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int step = 0;
            q.add(new Vertex(x, y, step));
        }
        for (int i = 0; i < k; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int z = in.nextInt();
            map[x][y] = z;
        }

        for (int i = 0; i < d; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            vis[x][y] = true;

        }
        in.close();
        long cnt = 0;
        long ans = 0;

        while (!q.isEmpty()) {
            Vertex u = q.remove();

            for (int i = 0; i < 4; i++) {
                Vertex tem = new Vertex();

                tem.x = u.x;
                tem.y = u.y;
                tem.step = u.step;
                tem.x += move[i][0];
                tem.y += move[i][1];
                tem.step++;
                if (tem.x > 0 && tem.y <= size && tem.y > 0 && tem.x <= size
                        && !vis[tem.x][tem.y]) {
                    vis[tem.x][tem.y] = true;
                    if (map[tem.x][tem.y] != 0) {
                        ans += map[tem.x][tem.y] * tem.step;
                        ++cnt;
                        if (cnt == k) {
                            break;
                        }
                    }
                    q.add(tem);
                }
            }
        }

        System.out.println(ans);

    }
}