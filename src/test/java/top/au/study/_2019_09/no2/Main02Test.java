package top.au.study._2019_09.no2;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

class Main02Test {

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

        System.setIn(new ByteArrayInputStream(dataInput.getBytes()));

        // 分配空间
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        // 将原本输出到控制台Console的字符流 重定向 到 bytes
        System.setOut(new PrintStream(bytes));

        //执行要测试的程序
        Main02.main(new String[]{});

        // bytes.toString() 作用是将 bytes内容 转换为字符流
        // 精确匹配
//        Assertions.assertEquals(expectedOutput,bytes.toString());
        // 模糊匹配
//        Assert.assertThat(bytes.toString(),
//                CoreMatchers.containsString(expectedOutput));
        MatcherAssert.assertThat(bytes.toString(),
                CoreMatchers.containsString(expectedOutput));
    }
}