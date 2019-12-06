package top.au.study._2019_03.no2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

}