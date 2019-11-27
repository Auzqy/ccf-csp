package top.au.study._2019_09.no2;

import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * description:
 *      参考：
 *      https://www.cnblogs.com/juking/p/4850649.html
 *      https://blog.csdn.net/xiaoQL520/article/details/98874486
 *      https://blog.csdn.net/hbmovie/article/details/80402380
 *      https://blog.csdn.net/hbmovie/article/details/80402380
 *      https://blog.csdn.net/hit1160300508/article/details/80299998
 *
 *      junit5
 *      https://blog.csdn.net/HD243608836/article/details/93649421
 *
 * createTime: 2019-11-28 00:11
 * @author au
 */
class Main01Test {


//    @Rule
//    public final SystemOutRule log = new SystemOutRule().enableLog();
//    public final SystemOutRule mute = new SystemOutRule().mute();

    static Stream<Arguments> stringExpected_systemStdin() {
        return Stream.of(
                Arguments.of(
                        "222 1 0",
                        "4\n" +
                                "4 74 -7 -12 -5\n" +
                                "5 73 -8 -6 59 -4\n" +
                                "5 76 -5 -10 60 -2\n" +
                                "5 80 -6 -15 59 0"),
                Arguments.of("39 4 2",
                        "5\n" +
                                "4 10 0 9 0\n" +
                                "4 10 -2 7 0\n" +
                                "2 10 0\n" +
                                "4 10 -3 5 0\n" +
                                "4 10 -1 8 0")
        );
    }


    @ParameterizedTest
    @MethodSource("stringExpected_systemStdin")
    void main(String expectedOutput, String dataInput) {
//        log.clearLog();
//        mute.clearLog();


//        InputStream stdin = System.in;
//        try {
            System.setIn(new ByteArrayInputStream(dataInput.getBytes()));

//        } finally {
//            System.setIn(stdin);
//        }

//        //执行要测试的程序
//        Main01.main(new String[]{});

        //对控制台的输出进行部分匹配断言
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));

//        assertThat(log.getLog(), containsString(expectedOutput));
//        assertThat(mute.getLog(), containsString(expectedOutput));
//        assertThat(outContent.toString(), containsString(expectedOutput));
//        Assertions.assertEquals(expectedOutput,outContent.toString());



        PrintStream console = null;          // 声明（为null）：输出流 (字符设备) console
        ByteArrayOutputStream bytes = null;  // 声明（为null）：bytes 用于缓存console 重定向过来的字符流

            bytes = new ByteArrayOutputStream();    // 分配空间
            console = System.out;                   // 获取System.out 输出流的句柄

            System.setOut(new PrintStream(bytes));  // 将原本输出到控制台Console的字符流 重定向 到 bytes

        //执行要测试的程序
        Main01.main(new String[]{});



        // bytes.toString() 作用是将 bytes内容 转换为字符流
        Assertions.assertEquals(expectedOutput,bytes.toString());

//        System.setOut(console);
    }
}