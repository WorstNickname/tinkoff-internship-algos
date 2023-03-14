package task3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import task3.Solution;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @ParameterizedTest
    @MethodSource("sources")
    void solutionTest(int length, String str, int expectedResult) {
        var actualResult = Solution.getMinGoodSubstring(length, str);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    static Stream<Arguments> sources() {

        var longString = Stream.generate(() -> "abcd").limit(200000).collect(Collectors.joining());

        return Stream.of(
                Arguments.of(12, "aabbccddbadd", 5),
                Arguments.of(16, "aaaabbbbccccdddd", 10),
                Arguments.of(7, "dbbccca", 7),
                Arguments.of(7, "abcabac", -1),
                Arguments.of(4, "abcd", 4),
                Arguments.of(8, "abcdabcd", 4),
                Arguments.of(200000, longString, 4)
        );
    }
}