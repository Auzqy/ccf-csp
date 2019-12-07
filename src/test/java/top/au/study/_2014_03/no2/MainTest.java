package top.au.study._2014_03.no2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    
    static Stream<Arguments> stringExpected_stringInput() {
        return Stream.of(
                Arguments.of("2\n" +
                        "1\n" +
                        "1\n" +
                        "IGNORED",

                        "3 4\n" +
                                "0 0 4 4\n" +
                                "1 1 5 5\n" +
                                "2 2 6 6\n" +
                                "1 1\n" +
                                "0 0\n" +
                                "4 4\n" +
                                "0 5")
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
         Assertions.assertEquals(expectedOutput,bytes.toString());
    }
    

}