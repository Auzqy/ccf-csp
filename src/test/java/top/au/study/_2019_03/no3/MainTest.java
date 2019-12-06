package top.au.study._2019_03.no3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

class MainTest {
    
    static Stream<Arguments> stringExpected_stringInput() {
        return Stream.of(
                Arguments.of("00010203\n" +
                        "04050607",

                        "2 1 2\n" +
                                "0 000102030405060710111213141516172021222324252627\n" +
                                "1 000102030405060710111213141516172021222324252627\n" +
                                "2\n" +
                                "0\n" +
                                "1"),
                Arguments.of("A0A1A2A3\n" +
                                "A0A0A0A0",

                        "3 2 2\n" +
                                "0 000102030405060710111213141516172021222324252627\n" +
                                "1 A0A1A2A3A4A5A6A7B0B1B2B3B4B5B6B7C0C1C2C3C4C5C6C7\n" +
                                "2\n" +
                                "2\n" +
                                "5")
        );
    }
    
    @ParameterizedTest
    @MethodSource("stringExpected_stringInput")
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