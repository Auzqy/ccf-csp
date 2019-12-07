package top.au.study._2013_12.no2;

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

    static Stream<Arguments> StringExpected_StringInput() {
        return Stream.of(
                Arguments.of("Right","0-670-82162-4"),
                Arguments.of("Right","6-670-82162-X"),
                Arguments.of("0-670-82162-4","0-670-82162-0")
        );
    }
    
    @ParameterizedTest
    @MethodSource("StringExpected_StringInput")
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