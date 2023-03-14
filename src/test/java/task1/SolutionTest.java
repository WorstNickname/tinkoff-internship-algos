package task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import task5.SolutionVer2;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private static final String YES = "YES";
    private static final String NO = "NO";

    @ParameterizedTest
    @MethodSource("sources")
    void solutionTest(String[] array, String expected) {
        var actualResult = Solution.isOrdered(array);

        assertThat(actualResult).isEqualTo(expected);
    }

    static Stream<Arguments> sources() {
        return Stream.of(
                Arguments.of(new String[]{"1", "2", "3", "4"}, YES),
                Arguments.of(new String[]{"7", "6", "5", "5"}, YES),
                Arguments.of(new String[]{"4", "4", "4", "4"}, YES),
                Arguments.of(new String[]{"5", "2", "3", "1"}, NO),
                Arguments.of(new String[]{"10", "10", "10", "10"}, YES),
                Arguments.of(new String[]{"300", "300", "300", "300"}, YES),
                Arguments.of(new String[]{"100", "200", "300", "200"}, NO),
                Arguments.of(new String[]{"10", "20", "19", "50"}, NO),
                Arguments.of(new String[]{"199", "76", "157", "50"}, NO)
        );
    }
}
