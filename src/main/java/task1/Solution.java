package task1;

import java.util.Scanner;

public class Solution {

    public static String isOrdered(String queue) {
        boolean increasing = false;
        boolean decreasing = false;
        for (int i = 0; i < queue.length() - 1; i++) {
            if (queue.charAt(i + 1) > queue.charAt(i)) {
                increasing = true;
            }

            if (queue.charAt(i + 1) < queue.charAt(i)) {
                decreasing = true;
            }

            if (increasing && decreasing) {
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
