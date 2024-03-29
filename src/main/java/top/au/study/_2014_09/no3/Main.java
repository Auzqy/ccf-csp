package top.au.study._2014_09.no3;

import java.util.Scanner;

/**
 *
 * description:
 *
 * 试题编号：	201409-3
 * 试题名称：	字符串匹配
 * 时间限制：	1.0s
 * 内存限制：	256.0MB
 * 问题描述：
 * 问题描述
 * 　　给出一个字符串和多行文字，在这些文字中找到字符串出现的那些行。你的程序还需支持大小写敏感选项：当选项打开时，表示同一个字母的大写和小写看作不同的字符；当选项关闭时，表示同一个字母的大写和小写看作相同的字符。
 * 输入格式
 * 　　输入的第一行包含一个字符串S，由大小写英文字母组成。
 * 　　第二行包含一个数字，表示大小写敏感的选项，当数字为0时表示大小写不敏感，当数字为1时表示大小写敏感。
 * 　　第三行包含一个整数n，表示给出的文字的行数。
 * 　　接下来n行，每行包含一个字符串，字符串由大小写英文字母组成，不含空格和其他字符。
 * 输出格式
 * 　　输出多行，每行包含一个字符串，按出现的顺序依次给出那些包含了字符串S的行。
 * 样例输入
 * Hello
 * 1
 * 5
 * HelloWorld
 * HiHiHelloHiHi
 * GrepIsAGreatTool
 * HELLO
 * HELLOisNOTHello
 * 样例输出
 * HelloWorld
 * HiHiHelloHiHi
 * HELLOisNOTHello
 * 样例说明
 * 　　在上面的样例中，第四个字符串虽然也是Hello，但是大小写不正确。如果将输入的第二行改为0，则第四个字符串应该输出。
 * 评测用例规模与约定
 * 　　1<=n<=100，每个字符串的长度不超过100。
 *
 * 满分通过
 *
 * createTime: 2019-12-09 11:41
 * @author au
 */
public class Main {

    private String s;
    private int saseSensitive;
    private String[] content;

    private void initData() {
        Scanner scanner = new Scanner(System.in);

        s = scanner.next();

        saseSensitive = scanner.nextInt();

        int n = scanner.nextInt();

        content = new String[n];

        for (int i = 0; i < n; i++) {
            content[i] = scanner.next();
        }
    }

    private void containsS() {
        int length = content.length;
        for (int i = 0; i < length; i++) {
            if (saseSensitive == 1) {
                if (content[i].contains(s)) {
                    print(length, i);
                }
            } else {
                if (content[i].toLowerCase().contains(s.toLowerCase())) {
                    print(length, i);
                }
            }
        }
    }

    private void print(int length, int i) {
        if (i < length - 1) {
            System.out.println(content[i]);
        } else {
            System.out.print(content[i]);
        }
    }

    public Main() {
        initData();
        containsS();
    }

    public static void main(String[] args) {
        new Main();
    }
}
