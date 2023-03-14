package task4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import task4.Solution;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @ParameterizedTest
    @MethodSource("sources")
    void solutionTest(int length, int[] array, int expectedResult) {
        var actualResult = Solution.findMaxBoringPrefix(length, array);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    static Stream<Arguments> sources() {

        var maxBoundaryArray = IntStream.generate(() -> 200000).limit(200000).toArray();

        return Stream.of(
                Arguments.of(13, new int[]{1, 2, 3, 1, 2, 2, 3, 3, 3, 1, 4, 4, 5}, 10),
                Arguments.of(10, new int[]{1, 2, 4, 2, 3, 1, 3, 9, 15, 23}, 7),
                Arguments.of(5, new int[]{1, 2, 3, 4, 5}, 5),
                Arguments.of(9, new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3}, 7),
                Arguments.of(11, new int[]{1, 1, 1, 1, 1, 2, 3, 2, 3, 2, 3}, 6),
                Arguments.of(3, new int[]{5, 5, 5}, 3),
                Arguments.of(7, new int[]{7, 7, 7, 7, 7, 7, 7}, 7),
                Arguments.of(200000, maxBoundaryArray, 200000),
                Arguments.of(2, new int[]{2, 2}, 2),
                Arguments.of(2, new int[]{200000, 200000}, 2),
                Arguments.of(2, new int[]{200000, 199999}, 2)
        );
    }
}