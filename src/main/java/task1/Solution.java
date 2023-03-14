package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static String isOrdered(String[] heights) {
        boolean isIncreasing = false;
        boolean isDecreasing = false;
        for (int i = 0; i < heights.length - 1; i++) {
            if (Integer.parseInt(heights[i + 1]) > Integer.parseInt(heights[i])) {
                isIncreasing = true;
            } else if (Integer.parseInt(heights[i + 1]) < Integer.parseInt(heights[i])) {
                isDecreasing = true;
            }

            if (isIncreasing && isDecreasing) {
                return "NO";
            }
        }
        return "YES";
    }

    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] heights = reader.readLine().split("\\s");
            System.out.println(isOrdered(heights));
        }
    }

}
