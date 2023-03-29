package task5;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SolutionVer2 {

    public static int findNormalSubarraysCount(int length,
                                               int[] balance) {
        long sum = 0;
        int result = 0;
        Map<Long, Integer> sumMap = new HashMap<>();
        sumMap.put(sum, 1);

        for (int i = 0; i < length; i++) {
            sum += balance[i];
            if (sumMap.containsKey(sum)) {
                result = result + (length - i);
            }
            sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            int[] a = Arrays.stream(reader.readLine().split("\\s"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            System.out.println(findNormalSubarraysCount(n, a));
        }
    }

}
