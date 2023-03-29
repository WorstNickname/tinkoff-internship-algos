package task6;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @ParameterizedTest
    @MethodSource("sources")
    void solutionTest(int studCount, int limitSum, int[][] studMarks, int expected) {
        int minMarksSum = 0;
        int maxMarksSum = 0;
        for (int i = 0; i < studCount; i++) {
            minMarksSum += studMarks[i][0];
            maxMarksSum += studMarks[i][1];
        }
        var actualResult = Solution.findMaxMedian(studCount, limitSum, studMarks, minMarksSum, maxMarksSum);

        assertThat(actualResult).isEqualTo(expected);
    }

    static Stream<Arguments> sources() {
        return Stream.of(
                Arguments.of(3, 27, new int[][] {{11, 14}, {2, 10}, {11, 14}}, 12),
                Arguments.of(7, 42, new int[][] {{5, 5}, {3, 5}, {7, 9}, {6, 7}, {3, 8}, {10, 10}, {1, 1}}, 7),
                Arguments.of(5, 5, new int[][] {{1, 100}, {1, 100}, {1, 100}, {1, 100}, {1, 100}}, 1),
                Arguments.of(5, 500, new int[][] {{1, 100}, {1, 100}, {1, 100}, {1, 100}, {1, 100}}, 100),
                Arguments.of(5, 1, new int[][] {{1, 100}, {1, 100}, {1, 100}, {1, 100}, {1, 100}}, -1)
        );
    }
}