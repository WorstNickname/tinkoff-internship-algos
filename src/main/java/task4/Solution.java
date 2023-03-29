package task4;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

    public static int findMaxBoringPrefix(int length,
                                          int[] a) {
        int[] numsMap = new int[200001];
        int[] freqMap = new int[length + 1];

        for (int i = 0; i < length; i++) {
            numsMap[a[i]]++;
            freqMap[numsMap[a[i]]]++;
        }

        for (int i = length - 1; i > 0; i--) {
            if (numsMap[a[i]] * freqMap[numsMap[a[i]]] != i) {
                freqMap[numsMap[a[i]]]--;
                numsMap[a[i]]--;
                if (numsMap[a[i - 1]] * freqMap[numsMap[a[i - 1]]] == i) {
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
            int n = Integer.parseInt(reader.readLine());
            int[] a = Arrays.stream(reader.readLine().split("\\s"))
                            .mapToInt(Integer::parseInt)
                            .toArray();
            System.out.println(findMaxBoringPrefix(n, a));
        }
    }

}
