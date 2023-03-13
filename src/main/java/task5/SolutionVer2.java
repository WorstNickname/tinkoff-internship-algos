package task5;

import java.util.HashMap;
/*
*  O(N)
* */
public class SolutionVer2 {

    public static int days(int length, int[] days) {
        int sum = 0;
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < days.length; i++) {
            sum += days[i];
            if (map.containsKey(sum)) {
                res = res + (length - i);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(days(4, new int[]{0, -1, -1, 1}));
        System.out.println(days(3, new int[]{42, -42, 42}));

    }
}
