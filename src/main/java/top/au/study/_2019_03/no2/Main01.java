package top.au.study._2019_03.no2;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * description:
 * 试题编号：	201903-2
 * 试题名称：	二十四点
 * 时间限制：	1.0s
 * 内存限制：	512.0MB
 *
 * 满分通过
 *
 * createTime: 2019-12-06 11:25
 * @author au
 */
public class Main01 {

    static final String YES = "Yes";
    static final String NO = "No";

    int N;

    public Main01() {
        initData();
    }

    /**
     * 数据初始化及计算
     */
    void initData() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        for (int i = 0; i < N; i++) {
            String s = scanner.next();
            if (i < N - 1) {
                System.out.println(calculate(s));
            } else {
                System.out.print(calculate(s));
            }
        }
    }

    /**
     * 表达式计算的核心逻辑
     * @param expression    待计算的表达式
     * @return
     */
    String calculate(String expression) {
        LinkedList<Integer> numList = new LinkedList<>();
        LinkedList<Character> opList = new LinkedList<>();

        int length = expression.length();

        for (int i = 0; i < length; i++) {
            char c = expression.charAt(i);
            // 数字
            if (isNum(c)) {
                numList.add(c - '0');
            }
            // 运算符号
            else {
                if (isMultiply(c)) {
                    Integer numBefore = numList.removeLast();
                    char c2 = expression.charAt(i + 1);
                    int tmp = numBefore * (c2 - '0');
                    numList.add(tmp);
                    // 维护一下变量 i
                    i = i + 1;
                } else if (isDivide(c)) {
                    Integer numBefore = numList.removeLast();
                    char c2 = expression.charAt(i + 1);
                    int tmp = numBefore / (c2 - '0');
                    numList.add(tmp);
                    // 维护一下变量 i
                    i = i + 1;
                } else {
                    opList.add(c);
                }
            }
        }

        int sum = numList.remove();
        while (numList.size() != 0) {
            Character op = opList.remove();
            Integer num = numList.remove();

            if (isAdd(op)) {
                sum += num;
            } else {
                sum -= num;
            }
        }

        return is24(sum);
    }

    private String is24(int sum) {
        if (24 == sum) {
            return YES;
        } else {
            return NO;
        }
    }

    private boolean isAdd(Character op) {
        return op == '+';
    }


    private boolean isDivide(char c) {
        return c == '/';
    }

    private boolean isMultiply(char c) {
        return c == 'x';
    }

    private boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {
        new Main01();
    }
}
