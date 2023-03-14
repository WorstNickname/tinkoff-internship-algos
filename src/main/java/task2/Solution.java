package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

    public static int getTimeForReviews(int juniors, int seniors, int reviews) {
        int time = 0;
        int availableSeniors = seniors;
        int reqReviews;
        for (int i = 1; i <= juniors; i++) {
            reqReviews = reviews;
            if (reqReviews == availableSeniors) {
                time++;
                availableSeniors = seniors;
                continue;
            }

            if (reqReviews > availableSeniors) {
                reqReviews -= availableSeniors;
                time++;
                availableSeniors = seniors;
            }

            if (reqReviews < availableSeniors) {
                if (i == juniors) {
                    time += 1;
                    break;
                } else {
                    availableSeniors -= reqReviews;
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
