package task1;

import java.util.Scanner;

public class Solution {

    public static String isOrdered(String order) {
        boolean isIncreasing = false;
        boolean isDecreasing = false;
        for (int i = 0; i < order.length() - 1; i++) {
            if (order.charAt(i + 1) > order.charAt(i)) {
                isIncreasing = true;
            }

            if (order.charAt(i + 1) < order.charAt(i)) {
                isDecreasing = true;
            }

            if (isIncreasing && isDecreasing) {
                return "NO";
            }
        }
        return "YES";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().replaceAll(" ", "");
        System.out.println(isOrdered(s));
    }

}
