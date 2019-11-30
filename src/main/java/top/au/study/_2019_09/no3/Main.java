package top.au.study._2019_09.no3;

import java.util.Objects;
import java.util.Scanner;

/**
 * description:
 * 试题编号：	201909-3
 * 试题名称：	字符画
 * 时间限制：	5.0s
 * 内存限制：	512.0MB
 *
 * ascii 对照表： https://blog.csdn.net/xiaokui_wingfly/article/details/49228459
 *
 *
 * todo 得分 0满分（运行耗时 843ms）
 *
 * createTime: 2019-11-29 13:27
 * @author au
 */
public class Main {

    private static final String ESC_RESET = "\033[0m";
    private static final String DEFAULT_FRONT_COLOR = "ffffff";
    private static final String DEFAULT_BACK_COLOR = "000000";

    private static final RGB DEFAULT_WHITE = new RGB(255, 255, 255);
    private static final RGB DEFAULT_BLACK = new RGB(0, 0, 0);

    /**
     * m: 图片的宽
     * n: 图片的高
     */
    private int m, n;

    /**
     * p: 每一个小块的宽度
     * q: 每一个小块的高度
     */
    private int p, q;

    /**
     * 存储像素矩阵点的数组
     */
    private String[][] pxMatrix;

    /**
     * 用于存储分块后的色块颜色
     * todo 不包括 #
     */
    private RGB[][] pxMatrixNew;

    void initData() {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();
        p = scanner.nextInt();
        q = scanner.nextInt();

        // 开空间
        pxMatrix = new String[n][m];

        // 初始化参数
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pxMatrix[i][j] = unifiedInput(scanner);
            }
        }

        pxMatrixNew = new RGB[n/q][m/p];

        calculateNewBlockColor();
    }

    /**
     * 拼接前景颜色的转义字符串
     * @param rgb
     * @return
     */
    String joinFrontColorConvertStr(RGB rgb) {
        StringBuilder sb = new StringBuilder();
        sb.append("\\033[38;2;");
        sb.append(rgb.getR()).append(";");
        sb.append(rgb.getG()).append(";");
        sb.append(rgb.getB()).append("m");
        return sb.toString();
    }


    /**
     * 拼接背景颜色的转义字符串
     * @param rgb
     * @return
     */
    String joinBlackColorConvertStr(RGB rgb) {
        StringBuilder sb = new StringBuilder();
        sb.append("\033[48;2;");
        sb.append(rgb.getR()).append(";");
        sb.append(rgb.getG()).append(";");
        sb.append(rgb.getB()).append("m");
        return sb.toString();
    }

    /**
     * 将输入格式化，统一转换为 #xxxxxx 的形式
     *
     * 处理特殊的
     * #abc 为   #aabbcc
     * #a   为   #aaaaaa
     *
     * 这里扔掉之间的 '#' 号，为了后续计算方便。
     *
     * @param scanner
     * @return
     */
    String unifiedInput(Scanner scanner) {
        StringBuilder res = new StringBuilder();
//        res.append("#");
        /*
            https://www.cnblogs.com/advancedcz/p/10511901.html
            https://blog.csdn.net/superme_yong/article/details/80543995
            今天在练习中遇到了调用Scanner类中的nextLine()输入字符串自动跳过的问题，
            在博客上看了两篇解答，原来是nextLine()误认了前面next（）输入时的Enter，
            但还是想了一会儿才弄清楚，这里再做个小总结。

            1.在next()和nextLine()方法连用时，
            nextLine()会在刚刚输入时就误认了前面next()结束输入的Enter，
            因此还没等输入就直接结束了；

            2.那么为什么反过来nextLine()在前、next()在后，next()就没有这个问题呢?
              这是因为next()方法在输入时要遇到一个有效字符后再遇到Enter才会结束，
              因此当它刚输入就遇到Enter是不会结束的，也就可以继续输入了。

            3.解决方法:如果一定要用nextLine()，
             可以在nextLine()前再添加一个nextLine()用于回收Enter，
             这样下一个nextLine()就可以正常输入了。
         */
        String curPx = scanner.next();
        if (2 == curPx.length()) {
            String pxVal = curPx.substring(1);
            for (int i = 0; i < 6; i++) {
                res.append(pxVal);
            }
            return res.toString();
        } else if (4 == curPx.length()) {
            for (int i = 1; i < 4; i++) {
                res.append(curPx.charAt(i));
                res.append(curPx.charAt(i));
            }
            return res.toString();
        } else {
            return curPx.substring(1);
        }
    }

    /**
     * 统一 ascii 的输出，以16进制的形式，并且要求大写
     * @param str
     * @return
     */
    String unifiedOutputByConvertStringToHex(String str){

        char[] chars = str.toCharArray();

        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < chars.length; i++){
            hex.append("\\x");
//            hex.append(Integer.toHexString(chars[i]).toUpperCase());
            hex.append(String.format("%02x", (int)chars[i]).toUpperCase());
        }

        return hex.toString();
    }

    /**
     * 依据入参获取对应的颜色
     * @param colorStr
     * @param colorFlag R, G, B
     * @return
     */
    String getXFromStr(String colorStr, String colorFlag) {
        if ("R".equalsIgnoreCase(colorFlag)) {
            return colorStr.substring(0, 2);
        } else if ("G".equalsIgnoreCase(colorFlag)) {
            return colorStr.substring(2, 4);
        } else if ("B".equalsIgnoreCase(colorFlag)) {
            return colorStr.substring(4);
        }
        throw new IllegalArgumentException("input colorFlag is not support!");
    }

    /**
     * 计算对应颜色的标记位
     * @param color1
     * @param color2
     * @param colorFlag
     * @return
     */
    String calculateXSum(String color1, String color2, String colorFlag) {
        Integer int1 = Integer.parseInt(
                getXFromStr(color1, colorFlag), 16);
        Integer int2 = Integer.parseInt(
                getXFromStr(color2, colorFlag), 16);
        return Integer.toHexString(int1 + int2);
    }

    /**
     * 计算分割后色块的颜色
     *
     * todo 这应该一次性的把R，G，B的数值都计算出来
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    String calculateBlockColor(int startX, int startY,
                               int endX, int endY,
                               String colorFlag) {
        int sum = 0;
        int count = 0;
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                sum += Integer.parseInt(
                        getXFromStr(pxMatrix[i][j], colorFlag),
                        16);
                count++;
            }
        }
        return Integer.toHexString(sum / count);
    }

    /**
     * 计算区块内的 RGB 颜色
     * index
     * 0    R
     * 1    G
     * 2    B
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return  数组内的值为十进制
     */
    int[] calculateBlockColor(int startX, int startY,
                               int endX, int endY) {
        int[] res = new int[3];
        int sumR = 0;
        int sumG = 0;
        int sumB = 0;
        int count = 0;
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                sumR += Integer.parseInt(
                        getXFromStr(pxMatrix[i][j], "R"),
                        16);
                sumG += Integer.parseInt(
                        getXFromStr(pxMatrix[i][j], "G"),
                        16);
                sumB += Integer.parseInt(
                        getXFromStr(pxMatrix[i][j], "B"),
                        16);
                count++;
            }
        }

        res[0] = sumR / count;
        res[1] = sumG / count;
        res[2] = sumB / count;
        return res;
    }

    /**
     * 字符转换的核心逻辑: 获得转义后的字符串
     *
     * @param pxMatrixNew
     * @return
     */
    String coreConvert(RGB[][] pxMatrixNew) {

        StringBuilder res = new StringBuilder();
        RGB last = DEFAULT_BLACK, start = DEFAULT_BLACK;
        for (int i = 0; i < pxMatrixNew.length; i++) {
            for (int j = 0; j < pxMatrixNew[i].length; j++) {
                /*
                 *  如果下一个字符的颜色刚好与默认值完全相同，
                 *  则直接使用重置转义序列
                 */
                if (pxMatrixNew[i][j].equals(DEFAULT_BLACK)) {
                    res.append(ESC_RESET);
                }else {
                    /*
                     *  如果某个字符的前景色/背景色与其前一个字符相同，
                     *  todo 或者对颜色的修改并不影响最终的显示效果，
                     *  则不出现更改这个属性的控制序列
                     */
                    if (!pxMatrixNew[i][j].equals(last)) {
                            res.append(
                                    joinBlackColorConvertStr(
                                            pxMatrixNew[i][j]));
                    }
                }

//                if (!pxMatrixNew[i][j].equals(last)) {
//                    if (pxMatrixNew[i][j].equals(start)) {
//                        res.append(ESC_RESET);
//                    } else {
//                        res.append(
//                                joinBlackColorConvertStr(
//                                        pxMatrixNew[i][j]));
//                    }
//                }
                // 更新上一个元素的内容
                last = pxMatrixNew[i][j];
                res.append(" ");
            }
            /*
                在输出每一行字符后，如果终端颜色不是默认值，
                重置终端的颜色。
             */
            if (!pxMatrixNew[i][pxMatrixNew[i].length - 1]
                    .equals(DEFAULT_BLACK)) {
                res.append(ESC_RESET);
            }
            res.append("\n");
        }

        return res.toString();
    }

    /**
     * 计算每个小块的颜色
     */
    private void calculateNewBlockColor() {
        for (int i = 0; i < n; i+=q) {
            for (int j = 0; j < m; j+=p) {
                int startX = i;
                int startY = j;
                int endX = i+q-1;
                int endY = j+p-1;

                int[] colors = calculateBlockColor(
                        startX, startY, endX, endY);
                pxMatrixNew[i/q][j/p] = new RGB(
                        colors[0],colors[1],colors[2]);
            }
        }
    }

    public Main() {
        initData();
//        String result = unifiedOutputByConvertStringToHex(
//                coreConvert(pxMatrixNew));
//        System.out.print(result);
    }

    static class RGB {
        private int R;
        private int G;
        private int B;

        public int getR() {
            return R;
        }

        public void setR(int r) {
            R = r;
        }

        public int getG() {
            return G;
        }

        public void setG(int g) {
            G = g;
        }

        public int getB() {
            return B;
        }

        public void setB(int b) {
            B = b;
        }

        public RGB(int r, int g, int b) {
            R = r;
            G = g;
            B = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            RGB rgb = (RGB) o;
            return R == rgb.R &&
                    G == rgb.G &&
                    B == rgb.B;
        }

        @Override
        public int hashCode() {
            return Objects.hash(R, G, B);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        String result = main.unifiedOutputByConvertStringToHex(
                main.coreConvert(main.pxMatrixNew));
        System.out.print(result);
    }
}
