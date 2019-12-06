package top.au.study._2019_03.no2;

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

class Main01Test {

    static Stream<Arguments> stringExpected_stringExpressionInput() {

        return Stream.of(
                Arguments.of("Yes","9+3+4x3"),
                Arguments.of("No","5+4x5x5"),
                Arguments.of("No","7-9-9+8"),
                Arguments.of("Yes","5x6/5x4"),
                Arguments.of("Yes","3+5+7+9"),
                Arguments.of("No","1x1+9-9"),
                Arguments.of("No","1x9-5/9"),
                Arguments.of("No","8/5+6x9"),
                Arguments.of("Yes","6x7-3x6"),
                Arguments.of("Yes","6x4+4/5")
        );
    }

    @ParameterizedTest
    @MethodSource("stringExpected_stringExpressionInput")
    void calculate(String expected,String expression) {
        Main01 main = new Main01();
        Assertions.assertEquals(expected,main.calculate(expression));
    }

    static Stream<Arguments> stringResult_stringExpression() {
        return Stream.of(
                Arguments.of("Yes\n" +
                        "No\n" +
                        "No\n" +
                        "Yes\n" +
                        "Yes\n" +
                        "No\n" +
                        "No\n" +
                        "No\n" +
                        "Yes\n" +
                        "Yes",

                        "10\n" +
                        "9+3+4x3\n" +
                        "5+4x5x5\n" +
                        "7-9-9+8\n" +
                        "5x6/5x4\n" +
                        "3+5+7+9\n" +
                        "1x1+9-9\n" +
                        "1x9-5/9\n" +
                        "8/5+6x9\n" +
                        "6x7-3x6\n" +
                        "6x4+4/5")
        );
    }

    @ParameterizedTest
    @MethodSource("stringResult_stringExpression")
    void mainTest(String expectedOutput, String consoleInput) {

        // 将原本从控制台输入的内容，输入到 ByteArrayInputStream
        System.setIn(new ByteArrayInputStream(consoleInput.getBytes()));

        // 分配空间
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        // 将原本输出到控制台Console的字符流 重定向 到 bytes
        System.setOut(new PrintStream(bytes));

        //执行要测试的程序，eg. Main.main(new String[]{});
        Main01.main(new String[]{});

        // bytes.toString() 作用是将 bytes内容 转换为字符流
        Assertions.assertEquals(expectedOutput,bytes.toString());
    }


}