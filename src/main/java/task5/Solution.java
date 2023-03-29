package task5;

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

}
