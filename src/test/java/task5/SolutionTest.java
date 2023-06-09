package task5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


class SolutionTest {

    @ParameterizedTest
    @MethodSource("sources")
    void solutionTest(int length, int[] array, int expected) {
        var actualResult = Solution.days(length, array);

        assertThat(actualResult).isEqualTo(expected);
    }

    static Stream<Arguments> sources() {
        return Stream.of(
                Arguments.of(1, new int[]{0}, 1),
                Arguments.of(1, new int[]{1}, 0),
                Arguments.of(1, new int[]{-2}, 0),
                Arguments.of(2, new int[]{0, 0}, 3),
                Arguments.of(2, new int[]{1, 1}, 0),
                Arguments.of(3, new int[]{42, -42, 42}, 3),
                Arguments.of(4,  new int[]{1, 2, 3, -6}, 1),
                Arguments.of(5, new int[]{-1, 1, 2, -3, 6}, 6),
                Arguments.of(2, new int[]{1337, -1337}, 1),
                Arguments.of(4, new int[]{-100, 100, -100, 100}, 6),
                Arguments.of(4, new int[]{0, 0, 0, 1}, 9),
                Arguments.of(4, new int[]{0, -1, -1, 1}, 5),
                Arguments.of(5,  new int[]{0, -1, -1, 1, -1}, 8),
                Arguments.of(200000, IntStream.generate(() -> 1000000000).limit(200000).toArray(), 0)
        );
    }

}