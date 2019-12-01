package top.au.study._2019_09.no4;

import java.util.*;

/**
 * description:
 *
 * 试题编号：	201909-4
 * 试题名称：	推荐系统
 * 时间限制：	5.0s
 * 内存限制：	512.0MB
 *
 *
 * 得了10分，先放放，可能是题意理解的不是特别清楚
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
     * 各种类所有商品的初始对象
     * 数组下表对应类别编号
     * 每个数组中的内容为TreeMap结构，
     * 内部已经按照比较函数的形式排列好
     * 值用来存储分数
     */
    private TreeMap<Product, Integer>[] productStates;

    /**
     * 用于存储编号对应的产品
     * key  type+commodity
     * value    包括分数的产品对象
     */
    private HashMap<String, Product> uniqueProduct;
    /**
     * 操作总数
     */
    private int op_num;
    /**
     * 查询操作的个数
     */
    private int op_ask;

    /**
     * 每次查询操作计划取出的总数
     */
    private int totalK;

//    private ArrayList<OperateProduct> operateProductArrayList;

    /**
     * 用于记录所有种类中所有商品的最大分数
     */
    private int maxScore;
    /**
     * 封装查询后的结果
     */
    private String resSr;

    /**
     * 商品的封装类
     */
    private class Product implements Comparable<Product>{
        /**
         * 商品类别编号
         */
        private int type;
        /**
         * 商品编号
         */
        private int commodity;
        /**
         * 商品的分数
         */
        private int score;

        public Product(int type, int commodity, int score) {
            this.type = type;
            this.commodity = commodity;
            this.score = score;
        }

        public Product(int type, int commodity) {
            this.type = type;
            this.commodity = commodity;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getCommodity() {
            return commodity;
        }

        public void setCommodity(int commodity) {
            this.commodity = commodity;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Product product = (Product) o;
            return type == product.type &&
                    commodity == product.commodity;
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, commodity);
        }

        /**
         * 分数高的优先;
         * 如果第a类商品中的编号为b的商品和
         * 第A类商品中的编号为B的得分相同：
         * 1。 当 a=A 时，选取编号为min(b,B)的商品
         * 2。 当 a!=A 时，选取第min(a,A)的商品
         *
         * @param another
         * @return
         */
        @Override
        public int compareTo(Product another) {
            if (this.score - another.score != 0) {
                return another.score - this.score;
            } else {
                if (this.type == another.type) {
                    return this.commodity - another.commodity;
                } else {
                    return this.type - another.type;
                }
            }
        }
    }

    /**
     * 对商品的操作类
     */
    private class OperateProduct {
        /**
         * 操作类型代码
         * 1：   增
         * 2：   删
         * 3：   查
         */
        private int opTypeNum;

        /**
         * 商品的类别编号
         */
        private int typeNum;

        /**
         * 商品的具体编号
         */
        private int commodityNum;

        /**
         * 商品的分数
         */
        private int scoreNum;

        /**
         * 查询时最大的商品数量
         */
        private int[] kArrNum;

        public int getOpTypeNum() {
            return opTypeNum;
        }

        public void setOpTypeNum(int opTypeNum) {
            this.opTypeNum = opTypeNum;
        }

        public int getTypeNum() {
            return typeNum;
        }

        public void setTypeNum(int typeNum) {
            this.typeNum = typeNum;
        }

        public int getCommodityNum() {
            return commodityNum;
        }

        public void setCommodityNum(int commodityNum) {
            this.commodityNum = commodityNum;
        }

        public int getScoreNum() {
            return scoreNum;
        }

        public void setScoreNum(int scoreNum) {
            this.scoreNum = scoreNum;
        }

        /**
         * 为了保证 get 后的安全性，这里返回原对象的 clone()
         * @return
         */
        public int[] getkArrNum() {
            return kArrNum.clone();
        }

        public void setkArrNum(int[] kArrNum) {
            this.kArrNum = kArrNum;
        }
    }



    void initData() {
        // 初始化所有种类中所有商品的最大分数
        maxScore = -1;

        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();

//        id_score = new HashMap[n];
//        for (int i = 0; i < n; i++) {
//            id_score[i] = new HashMap<>();
//            id_score[i].put(scanner.nextInt(), scanner.nextInt());
//        }

        uniqueProduct = new HashMap<>(m*n);

        productStates = new TreeMap[m];
        for (int i = 0; i < m; i++) {
            productStates[i] = new TreeMap<>();
        }
        for (int j = 0; j < n; j++) {
            int commodityInit = scanner.nextInt();
            int scoreInit = scanner.nextInt();
            for (int i = 0; i < m; i++) {
                Product product = new Product(i, commodityInit, scoreInit);
                productStates[i].put(product,product.getScore());
                uniqueProduct.put(i + commodityInit + "", product);
                modifiedMaxScoreGlobal(product);
            }
        }
        op_num = scanner.nextInt();
        // 初始化所有操作记录
        operateProductArrayListInitAndDoOp(scanner);
    }

    private void modifiedMaxScoreGlobal(Product product) {
        if (product.getScore() > maxScore) {
            maxScore = product.getScore();
        }
    }

    /**
     * 初始化所有操作记录 & 记录共有多少次查询操作
     * @param scanner
     */
    private void operateProductArrayListInitAndDoOp(Scanner scanner) {
        StringBuilder res = new StringBuilder();
//        operateProductArrayList = new ArrayList<>();
        for (int i = 0; i < op_num; i++) {
//            OperateProduct operateProduct = new OperateProduct();
            int inputOpTypeNum = scanner.nextInt();
//            operateProduct.setOpTypeNum(inputOpTypeNum);
            // 增
            if (1 == inputOpTypeNum) {
                int typeNum = scanner.nextInt();
                int commodityNum = scanner.nextInt();
                int scoreNum = scanner.nextInt();
//                operateProduct.setTypeNum(typeNum);
//                operateProduct.setCommodityNum(commodityNum);
//                operateProduct.setScoreNum(scoreNum);
                Product product = new Product(
                        typeNum, commodityNum, scoreNum);
                doInsert(product);
            }
            // 删
            else if (2 == inputOpTypeNum) {
                int typeNum = scanner.nextInt();
                int commodityNum = scanner.nextInt();
//                operateProduct.setTypeNum(typeNum);
//                operateProduct.setCommodityNum(commodityNum);
                Product product = new Product(typeNum, commodityNum);
                doDelete(product);
            }
            // 查询
            else if (3 == inputOpTypeNum) {
                int[] totalKAndMaxK = new int[m+1];
                for (int j = 0; j <= m; j++) {
                    totalKAndMaxK[j] = scanner.nextInt();
                }
//                operateProduct.setkArrNum(totalKAndMaxK);
//                // 记录共有多少次查询操作
//                op_ask++;
                totalK = totalKAndMaxK[0];
                // 这条语句需要执行 m 次数
                for (int c = 1; c <= m; c++) {
                    res.append(doFind(totalKAndMaxK, c));
                    res.append("\n");
                }
            }
//            operateProductArrayList.add(operateProduct);
        }

        /*
            去掉响应结果的最后一个换行符
            "\n" 算作一个字符
         */
        resSr = res.toString().substring(0, res.toString().length() - 1);
    }


    /**
     * 本次查询中
     * 第 c 类商品选出的商品编号，同类商品的编号从小到大输出
     * 如果没有任何商品，该行输出 -1
     */
    private String doFind(int[] totalKAndMaxK, int c) {
        StringBuilder res = new StringBuilder();
        int curK = totalKAndMaxK[c];
        for (Map.Entry<Product, Integer> productIntegerEntry : productStates[c - 1].entrySet()) {
//            if (curK > 0 && maxScore == productIntegerEntry.getValue()) {
            if (curK > 0 && totalK > 0) {
                if (curK == totalKAndMaxK[c]) {
                    res.append(productIntegerEntry.getKey().getCommodity());
                }else {
                    res.append(" ");
                    res.append(productIntegerEntry.getKey().getCommodity());
                }
                curK--;
                // 商品已选满k件，结束遍历
                totalK--;
                if (curK == 0 || totalK == 0) {
                    break;
                }
            } else if(curK == totalKAndMaxK[c]) {
                res.append("-1");
                break;
            }
//            else if (maxScore > productIntegerEntry.getValue()) {
//                break;
//            }
        }
        return res.toString();
    }

    /**
     * 删除商品 & 维护所有商品的最大分数
     * @param product
     */
    private void doDelete(Product product) {
        Product recordedProduct = uniqueProduct.get(
                product.getType() + product.getCommodity() + "");
//        if (productStates[product.getType()].containsKey(recordedProduct)) {
//            int curScore = productStates[product.getType()].get(product);
            int curScore = recordedProduct.getScore();
            // 删除商品
            productStates[product.getType()].remove(recordedProduct);
            // 维护所有商品的最大分数
            if (curScore == maxScore) {
                maxScore = -1;
                for (int i = 0; i < productStates.length; i++) {
                    modifiedMaxScoreGlobal(productStates[i].firstKey());
                }
            }
//        }
    }

    /**
     * 添加商品 & 维护所有商品的最大分数
     */
    private void doInsert(Product product) {
        // 添加商品
        productStates[product.getType()].put(product,product.getScore());
        uniqueProduct.put(
                product.getType() + product.getCommodity() + "",
                product);
        // 维护所有商品的最大分数
        modifiedMaxScoreGlobal(product);
    }

//    String executeOp(ArrayList<OperateProduct> operateProductArrayList) {
//        StringBuilder res = new StringBuilder();
//        for (int i = 0; i < op_ask; i++) {
//            for (int j = 1; j <= m; j++) {
//
//                /*
//                    目的就是最后一行不加换行符
//                    上下两种情况等价
//                    if(i!=op_ask-1 || j!=m)
//                 */
//                if (!(i == op_ask-1 && j ==m)) {
//                    res.append("\n");
//                }
//            }
//        }
//        return res.toString();
//    }


    public Main() {
        initData();
    }

    public static void main(String[] args) {
        Main main = new Main();
        System.out.print(main.resSr);
    }


}
