package top.au.study._2019_09.no3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {



    @Test
    void initData() {
    }

    @Test
    void joinFrontColorConvertStr() {
    }

    @Test
    void joinBlackColorConvertStr() {
    }

    @Test
    void unifiedInput() {
    }

    @Test
    void unifiedOutputByConvertStringToHex() {
    }

    @Test
    void getXFromStr() {
    }

    @Test
    void calculateXSum() {
    }

    @Test
    void calculateBlockColor() {
    }

    @Test
    void testCalculateBlockColor() {
    }

    @Test
    void coreConvert() {
    }

    static Stream<Arguments> StringExpected_StringInput() {
        return Stream.of(
                Arguments.of("\\x1B\\x5B\\x34\\x38\\x3B\\x32\\x3B\\x31\\x3B\\x32\\x3B\\x33\\x6D\\x20\\x1B\\x5B\\x30\\x6D\\x0A",
                        "1 1\n" +
                        "1 1\n" +
                        "#010203"),

                Arguments.of("\\x1B\\x5B\\x34\\x38\\x3B\\x32\\x3B\\x38\\x3B\\x38\\x3B\\x38\\x6D\\x20\\x20\\x1B\\x5B\\x30\\x6D\\x0A",
                        "2 2\n" +
                        "1 2\n" +
                        "#111111\n" +
                        "#0\n" +
                        "#000000\n" +
                        "#111")
        );
    }

    @ParameterizedTest
    @MethodSource("StringExpected_StringInput")
    void main (String expectedOutput, String consoleInput) {

        // 将原本从控制台输入的内容，输入到 ByteArrayInputStream
        System.setIn(new ByteArrayInputStream(consoleInput.getBytes()));

        // 分配空间
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        // 将原本输出到控制台Console的字符流 重定向 到 bytes
        System.setOut(new PrintStream(bytes));

        //执行要测试的程序，eg. Main.main(new String[]{});
        Main.main(new String[]{});

        // bytes.toString() 作用是将 bytes内容 转换为字符流
        /*
            // 精确匹配
            Assertions.assertEquals(expectedOutput,bytes.toString());
            // 模糊匹配
            Assert.assertThat(bytes.toString(), CoreMatchers.containsString(expectedOutput));
            // 模糊匹配
            MatcherAssert.assertThat(bytes.toString(), CoreMatchers.containsString(expectedOutput));
         */
         Assertions.assertEquals(expectedOutput,bytes.toString());
    }

}