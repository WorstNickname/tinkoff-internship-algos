package task6;

import java.util.*;

public class SolutionGPT {

    public static boolean isMedianFound(int[][] studMarks,
                                        int studCount,
                                        int limit,
                                        int median) {
        int[] requiredMarks = new int[studCount];
        for (int i = 0; i < studCount; i++) {
            if (studMarks[i][1] < median) {
                requiredMarks[i] = -1;
            } else {
                requiredMarks[i] = Math.max(studMarks[i][0], median);
            }
        }
        Arrays.sort(requiredMarks);
        int sum = 0;
        for (int i = studCount / 2; i < studCount; i++) {
            if (requiredMarks[i] == -1) {
                return false;
            }
            sum += requiredMarks[i];
            if (sum > limit) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int studCount = sc.nextInt();
        int limit = sc.nextInt();

        int[][] studMarks = new int[studCount][2];

        for (int i = 0; i < studCount; i++) {
            studMarks[i][0] = sc.nextInt();
            studMarks[i][1] = sc.nextInt();
        }

        int left = 1;
        int right = limit;
        while (left < right - 1) {
            int mid = (left + right) / 2;
            if (isMedianFound(studMarks, studCount, limit, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(left);
    }

}
