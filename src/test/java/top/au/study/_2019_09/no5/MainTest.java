package top.au.study._2019_09.no5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void c() {
    }

    // new ArrayList<Integer>[]{
    //                        new ArrayList<Integer>(Arrays.asList(1,2,3)),
    //                        new ArrayList<Integer>(Arrays.asList(1,2,3))}
    static Stream<Arguments> ArrayListExpected_intArrOriginArr_intK_ArrayListArrRes_intArrTmpArr() {
        return Stream.of(
                Arguments.of(),
                Arguments.of()
        );
    }
    
    @ParameterizedTest
    @MethodSource("ArrayListExpected_intArrOriginArr_intK_ArrayListArrRes_intArrTmpArr")
    void generalCombinationCalculation(int[] originArr,
                                       int k,
                                       ArrayList<Integer>[] res,
                                       int[] tmpArr) {
    }
}