package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

    public static int getTimeForReviews(int juniors,
                                        int seniors,
                                        int reviews) {
        int time = 0;
        int remainingSeniors = seniors;
        int remainingReviews;
        for (int i = 1; i <= juniors; i++) {
            remainingReviews = reviews;
            if (remainingReviews == remainingSeniors) {
                time++;
                remainingSeniors = seniors;
                continue;
            }

            if (remainingReviews > remainingSeniors) {
                remainingReviews -= remainingSeniors;
                time++;
                remainingSeniors = seniors;
            }

            if (remainingReviews < remainingSeniors) {
                if (i == juniors) {
                    time += 1;
                    break;
                } else {
                    remainingSeniors -= remainingReviews;
                }
            }
        }
        return time;
    }

    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine()
                    .split("\\s"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            System.out.println(getTimeForReviews(input[0], input[1], input[2]));
        }
    }

}
