package top.au.study._2013_12.no1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

class MainTest {

    static Stream<Arguments> intExpected_stringInput() {
        return Stream.of(
                Arguments.of("10","6\n" +
                        "10 1 10 20 30 20"),
                Arguments.of("1","10\n" +
                        "10 1 10 20 30 20 1 1 1 1")
        );
    }

    @ParameterizedTest
    @MethodSource("intExpected_stringInput")
    void mainTest(String expectedOutput, String consoleInput) {

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