package top.au.study._2014_03.no2;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * description:
 *
 * 试题编号：	201403-2
 * 试题名称：	窗口
 * 时间限制：	1.0s
 * 内存限制：	256.0MB
 * 问题描述：
 * 问题描述
 * 　　在某图形操作系统中,有 N 个窗口,每个窗口都是一个两边与坐标轴分别平行的矩形区域。窗口的边界上的点也属于该窗口。窗口之间有层次的区别,在多于一个窗口重叠的区域里,只会显示位于顶层的窗口里的内容。
 * 　　当你点击屏幕上一个点的时候,你就选择了处于被点击位置的最顶层窗口,并且这个窗口就会被移到所有窗口的最顶层,而剩余的窗口的层次顺序不变。如果你点击的位置不属于任何窗口,则系统会忽略你这次点击。
 * 　　现在我们希望你写一个程序模拟点击窗口的过程。
 * 输入格式
 * 　　输入的第一行有两个正整数,即 N 和 M。(1 ≤ N ≤ 10,1 ≤ M ≤ 10)
 * 　　接下来 N 行按照从最下层到最顶层的顺序给出 N 个窗口的位置。 每行包含四个非负整数 x1, y1, x2, y2,表示该窗口的一对顶点坐标分别为 (x1, y1) 和 (x2, y2)。保证 x1 < x2,y1 2。
 * 　　接下来 M 行每行包含两个非负整数 x, y,表示一次鼠标点击的坐标。
 * 　　题目中涉及到的所有点和矩形的顶点的 x, y 坐标分别不超过 2559 和　　1439。
 * 输出格式
 * 　　输出包括 M 行,每一行表示一次鼠标点击的结果。如果该次鼠标点击选择了一个窗口,则输出这个窗口的编号(窗口按照输入中的顺序从 1 编号到 N);如果没有,则输出"IGNORED"(不含双引号)。
 * 样例输入
 * 3 4
 * 0 0 4 4
 * 1 1 5 5
 * 2 2 6 6
 * 1 1
 * 0 0
 * 4 4
 * 0 5
 * 样例输出
 * 2
 * 1
 * 1
 * IGNORED
 * 样例说明
 * 　　第一次点击的位置同时属于第 1 和第 2 个窗口,但是由于第 2 个窗口在上面,它被选择并且被置于顶层。
 * 　　第二次点击的位置只属于第 1 个窗口,因此该次点击选择了此窗口并将其置于顶层。现在的三个窗口的层次关系与初始状态恰好相反了。
 * 　　第三次点击的位置同时属于三个窗口的范围,但是由于现在第 1 个窗口处于顶层,它被选择。
 * 　　最后点击的 (0, 5) 不属于任何窗口。
 *
 *
 *
 * createTime: 2019-12-07 23:13
 * @author au
 */
public class Main {

    /**
     * N: 窗口的个数
     * M: 点击的次数
     */
    private int N, M;

    /**
     * 依据从顶层向底层的顺序存储各个窗口
     */
    private LinkedList<Window> linkedList;

    private void initDataAndExecute() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();

        linkedList = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            int winNo = i + 1;
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            Window window = new Window(winNo, x1, y1, x2, y2);
            linkedList.addFirst(window);
        }

        for (int i = 0; i < M; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            String res = executeMouseClick(x, y);
            if (i < M - 1) {
                System.out.println(res);
            } else {
                System.out.print(res);
            }
        }
    }

    /**
     * 鼠标点击实践的核心逻辑
     * @param x
     * @param y
     * @return
     */
    private String executeMouseClick(int x, int y) {
        for (int i = 0; i < linkedList.size(); i++) {
            Window window = linkedList.get(i);
            if (isInWindow(window,x, y)) {
                changeWindowOrder(i);
                return String.valueOf(window.getNo());
            }
        }
        return "IGNORED";
    }

    /**
     * 改变当前窗口顺序
     * @param index 需要置顶的窗口的索引
     */
    private void changeWindowOrder(int index) {
        Window window = linkedList.get(index);
        linkedList.remove(index);
        linkedList.addFirst(window);
    }

    private boolean isInWindow(Window window, int x, int y) {
        return x <= window.getX2() && x >= window.getX1()
                && y <= window.getY2() && y >= window.getY1();
    }

    class Window {
        /**
         * 窗体的初始编号
         */
        private int No;
        private int x1;
        private int y1;
        private int x2;
        private int y2;

        public Window() {
        }

        public Window(int no, int x1, int y1, int x2, int y2) {
            No = no;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public int getNo() {
            return No;
        }

        public void setNo(int no) {
            No = no;
        }

        public int getX1() {
            return x1;
        }

        public void setX1(int x1) {
            this.x1 = x1;
        }

        public int getX2() {
            return x2;
        }

        public void setX2(int x2) {
            this.x2 = x2;
        }

        public int getY1() {
            return y1;
        }

        public void setY1(int y1) {
            this.y1 = y1;
        }

        public int getY2() {
            return y2;
        }

        public void setY2(int y2) {
            this.y2 = y2;
        }
    }

    public Main() {
        initDataAndExecute();
    }

    public static void main(String[] args) {
        new Main();
    }
}
