package task2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @ParameterizedTest
    @MethodSource("sources")
    void solutionTest(int juniors, int seniors, int reviews, int expectedTime) {
        var actualTime = Solution.getTimeForReviews(juniors, seniors, reviews);

        assertThat(actualTime).isEqualTo(expectedTime);
    }

    static Stream<Arguments> sources() {
        return Stream.of(
                Arguments.of(3, 2, 2, 3),
                Arguments.of(7, 3, 2, 5),
                Arguments.of(10000, 10000, 10000, 10000),
                Arguments.of(7, 6, 3, 4),
                Arguments.of(1, 1, 1, 1),
                Arguments.of(30, 20, 1, 2),
                Arguments.of(30, 20, 10, 15)
        );
    }
}