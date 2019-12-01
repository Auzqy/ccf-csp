package top.au.study._2019_09.no4;

import org.junit.Ignore;
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

    static Stream<Arguments> StringExpected_StringInput() {
        return Stream.of(
                Arguments.of("1\n" +
                        "1\n" +
                        "1 4\n" +
                        "1 2\n" +
                        "1\n" +
                        "1\n" +
                        "4\n" +
                        "1\n" +
                        "4\n" +
                        "-1",
                        "2 3\n" +
                        "1 3\n" +
                        "2 2\n" +
                        "3 1\n" +
                        "8\n" +
                        "3 100 1 1\n" +
                        "1 0 4 3\n" +
                        "1 0 5 1\n" +
                        "3 10 2 2\n" +
                        "3 10 1 1\n" +
                        "2 0 1\n" +
                        "3 2 1 1\n" +
                        "3 1 1 1"),
                Arguments.of("1\n" +
                                "1\n" +
                                "1 4\n" +
                                "1 2\n" +
                                "1\n" +
                                "1\n" +
                                "4\n" +
                                "1\n" +
                                "4\n" +
                                "1",
                        "2 3\n" +
                                "1 3\n" +
                                "2 2\n" +
                                "3 1\n" +
                                "8\n" +
                                "3 100 1 1\n" +
                                "1 0 4 3\n" +
                                "1 0 5 1\n" +
                                "3 10 2 2\n" +
                                "3 10 1 1\n" +
                                "2 0 1\n" +
                                "3 2 1 1\n" +
                                "3 2 1 1")
        );
    }

    @ParameterizedTest
    @MethodSource("StringExpected_StringInput")
    void main(String expectedOutput, String consoleInput) {
        
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
    
    
    @Test
    void initData() {
    }
}