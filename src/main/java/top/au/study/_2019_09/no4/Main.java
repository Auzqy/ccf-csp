package top.au.study._2019_09.no4;

import java.util.HashMap;
import java.util.Scanner;

/**
 * description:
 *
 * 试题编号：	201909-4
 * 试题名称：	推荐系统
 * 时间限制：	5.0s
 * 内存限制：	512.0MB
 *
 *
 *
 * createTime: 2019-11-30 11:34
 * @author au
 */
public class Main {

    /**
     * m: 商品的种类数，m<=50
     * n: 每类商品初始编号不同的商品数，n<=3*10^4
     */
    private int m,n;
    /**
     * 所有m类商品的第j个商品的编号和得分
     */
    private HashMap<Integer,Integer>[] id_score;
    /**
     * 操作总数
     */
    private int op_num;
    /**
     * 查询操作的个数
     */
    private int op_ask;


    void initData() {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();

        id_score = new HashMap[n];
        for (int i = 0; i < n; i++) {
            id_score[i] = new HashMap<>();
            id_score[i].put(scanner.nextInt(), scanner.nextInt());
        }
        op_num = scanner.nextInt();
    }
}
