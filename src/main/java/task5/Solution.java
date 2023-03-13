package task5;

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
        System.out.println(days(1, new int[]{0}));
        System.out.println(days(1, new int[]{1}));
        System.out.println(days(1, new int[]{-2}));
        System.out.println(days(3, new int[]{42, -42, 42}));
        System.out.println(days(4, new int[]{1, 2, 3, -6}));
        System.out.println(days(5, new int[]{-1, 1, 2, -3, 6}));
        System.out.println(days(4, new int[]{0, 0, 0, 1}));
    }
}
