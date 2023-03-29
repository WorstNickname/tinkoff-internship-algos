package task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static int getMinGoodSubstring(int length,
                                          String s) {
        if (length < 4 || s == null) {
            return -1;
        }

        int result = -1;
        int minGoodSubstring = length;
        int[] charMap = new int[4];
        int leftPointer = 0;
        int rightPointer = 0;

        charMap[s.charAt(rightPointer) - 'a']++;
        while (rightPointer < length) {
            if (allCharsExist(charMap)) {
                charMap[s.charAt(leftPointer) - 'a']--;
                int subStringLength = rightPointer - leftPointer + 1;
                minGoodSubstring = Math.min(subStringLength, minGoodSubstring);
                result = minGoodSubstring;
                leftPointer++;
            } else {
                rightPointer++;
                if (rightPointer < length) {
                    charMap[s.charAt(rightPointer) - 'a']++;
                }
            }
        }
        return result;
    }

    private static boolean allCharsExist(int[] map) {
        for (int i : map) {
            if (i <= 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            String s = reader.readLine();
            System.out.println(getMinGoodSubstring(n, s));
        }
    }

}
