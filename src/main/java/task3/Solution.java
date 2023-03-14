package task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static int getMinGoodSubstring(int length, String str) {
        int left = 0;
        int right = 0;
        int min = length;
        int result = -1;
        int[] hash = {0, 0, 0, 0};

        if (length < 4 || str.equals("")) {
            return result;
        }

        hash[str.charAt(right) - 'a']++;
        while (right < length) {
            if (hash[0] > 0 && hash[1] > 0 && hash[2] > 0 && hash[3] > 0) {
                hash[str.charAt(left) - 'a']--;
                int subStringLength = str.substring(left, right + 1).length();
                min = Math.min(subStringLength, min);
                result = min;
                left++;
            } else {
                right++;
                if (right < length) {
                    hash[str.charAt(right) - 'a']++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            int length = Integer.parseInt(reader.readLine());
            String str = reader.readLine();
            System.out.println(getMinGoodSubstring(length, str));
        }
    }

}
