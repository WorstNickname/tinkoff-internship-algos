package task6;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int studCount = scanner.nextInt();
        int limit = scanner.nextInt();

        int[] lowMarks = new int[studCount];
        int[] highMarks = new int[studCount];
        for (int i = 0; i < studCount; i++) {
            lowMarks[i] = scanner.nextInt();
            highMarks[i] = scanner.nextInt();
        }

        int left = 0;
        int right = limit;

        int maxMedian = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            int[] count = new int[limit + 1];
            for (int i = 0; i < studCount; i++) {
                if (lowMarks[i] > mid) {
                    continue;
                }
                count[Math.min(highMarks[i], mid)]++;
            }

            int sum = 0;
            int median = 0;
            for (int i = limit; i >= 0; i--) {
                sum += count[i];
                if (sum > studCount / 2) {
                    median = i;
                    break;
                }
                if (sum == studCount / 2 && studCount % 2 == 0) {
                    for (int j = i - 1; j >= 0; j--) {
                        if (count[j] > 0) {
                            median += j;
                            break;
                        }
                    }
                    median /= 2;
                    break;
                }
            }

            if (median > maxMedian) {
                maxMedian = median;
            }

            if (sum > studCount / 2) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(maxMedian);
    }
}

