package task4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

    public static int findMaxBoringPrefix(int length, int[] nums) {
        int[] numsMap = new int[200001];
        int[] freqMap = new int[length + 1];

        for (int i = 0; i < length; i++) {
            numsMap[nums[i]]++;
            freqMap[numsMap[nums[i]]]++;
        }

        for (int i = length - 1; i > 0; i--) {
            if (numsMap[nums[i]] * freqMap[numsMap[nums[i]]] != i) {
                freqMap[numsMap[nums[i]]]--;
                numsMap[nums[i]]--;
                if (numsMap[nums[i - 1]] * freqMap[numsMap[nums[i - 1]]] == i) {
                    return i + 1;
                }
            } else {
                return i + 1;
            }
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            int length = Integer.parseInt(reader.readLine());
            int[] array = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            System.out.println(findMaxBoringPrefix(length, array));
        }
    }
}
