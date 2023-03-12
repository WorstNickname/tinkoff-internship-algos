package task3;

public class Solution {

    public static int min(int length, String str) {
        int left = 0, right = 0, min = length, res = -1;

        if (length < 4 || str.equals("")) {
            return res;
        }
        //            a  b  c  d
        //            0  1  2  3
        int[] hash = {0, 0, 0, 0};
        hash[str.charAt(right) - 'a']++;
        while (right < length) {
            if (hash[0] > 0 && hash[1] > 0 && hash[2] > 0 && hash[3] > 0) {
                hash[str.charAt(left) - 'a']--;
                int subStringLength = str.substring(left, right + 1).length();
                if (subStringLength <= min) {
                    min = subStringLength;
                    res = min;
                }
                left++;
            } else {
                right++;
                if (right < length) {
                    hash[str.charAt(right) - 'a']++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(min(12, "aabbccddbadd"));
        System.out.println(min(16, "aaaabbbbccccdddd"));
        System.out.println(min(7, "dbbccca"));
        System.out.println(min(7, "abcabac"));
        System.out.println(min(4, "abcd"));
        System.out.println(min(4, "aaaa"));
        System.out.println(min(0, ""));
    }
}
