package task5;

import java.util.stream.IntStream;


/*
*
*  O(N^2)
*
* */
public class Solution {

    public static int days(int length, int[] days) {
        int left = 0;
        int res = 0;

        while (left < length) {
            int sum = 0;
            sum += days[left];
            if (sum == 0) {
                res = res + (length - left);
                left++;
            } else {
                for (int right = left + 1; right <= length; right++) {
                    if (right == length) {
                        left++;
                        break;
                    }
                    sum += days[right];
                    if (sum == 0) {
                        res = res + (length - right);
                        left++;
                        break;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(days(1, new int[]{0}));
//        System.out.println(days(1, new int[]{1}));
//        System.out.println(days(1, new int[]{-2}));
//        System.out.println(days(2, new int[]{0, 0}));
//        System.out.println(days(2, new int[]{1, 1}));
//        System.out.println(days(2, new int[]{1337, -1337}));
//        System.out.println(days(3, new int[]{42, -42, 42}));
//        System.out.println(days(4, new int[]{1, 2, 3, -6}));
//        System.out.println(days(5, new int[]{-1, 1, 2, -3, 6}));
//        System.out.println(days(4, new int[]{-100, 100, -100, 100}));
//        System.out.println(days(4, new int[]{0, 0, 0, 1}));
//        System.out.println(days(4, new int[]{0, -1, -1, 1}));
//        System.out.println(days(5, new int[]{0, -1, -1, 1, -1}));
//        System.out.println(days(6, new int[]{4, 2, -3, -1, 0, 4}));

        // 9+ seconds
        System.out.println(days(200000,  IntStream.generate(() -> 1000000000).limit(200000).toArray()));
    }
}
