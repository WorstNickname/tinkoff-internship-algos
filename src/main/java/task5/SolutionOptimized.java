package task5;

public class Solu2 {

    public static int days(int length, int[] days) {
        int left = 0;
        int right = 1;
        int res = 0;
        int sum = 0;
        sum += days[left];
        while (left < length) {
            if (sum == 0) {
                res = res + (length - left);
                left++;
            } else {
                while (right < length) {
                    sum += days[right];
                    if (sum == 0) {
                        res = res + (length - right);
                        right++;
                        break;
                    }
                    right++;
                }
                sum -= days[left];
                left++;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(days(1, new int[]{0}));
        System.out.println(days(1, new int[]{1}));
        System.out.println(days(1, new int[]{-2}));
        System.out.println(days(2, new int[]{0, 0}));
        System.out.println(days(2, new int[]{1, 1}));
        System.out.println(days(2, new int[]{1337, -1337}));
        System.out.println(days(3, new int[]{42, -42, 42}));
        System.out.println(days(4, new int[]{1, 2, 3, -6}));
        System.out.println(days(5, new int[]{-1, 1, 2, -3, 6}));
        System.out.println(days(4, new int[]{-100, 100, -100, 100}));
    }
}
