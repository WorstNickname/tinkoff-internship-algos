package task6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FinalSolution {

    public static void main(String[] args) throws IOException {

        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            String[] nums = line.split("\\s");
            int studCount = Integer.parseInt(nums[0]);
            int limitSum = Integer.parseInt(nums[1]);

            int[][] studMarks = new int[studCount][2];
            int sumMin = 0, sumMax = 0;
            for (int i = 0; i < studCount; i++) {
                nums = reader.readLine().split("\\s");
                studMarks[i][0] = Integer.parseInt(nums[0]);
                studMarks[i][1] = Integer.parseInt(nums[1]);
                sumMin += studMarks[i][0];
                sumMax += studMarks[i][1];
            }

            int result = findMaxMedian(studCount, limitSum, studMarks, sumMin, sumMax);
            System.out.println(result);
        }
    }

    public static int findMaxMedian(int studCount,
                                    int limitSum,
                                    int[][] studMarks,
                                    int minMarksSum,
                                    int maxMarksSum) {
        int medianValue = -1;

        if (minMarksSum > limitSum) {
            return medianValue;
        } else if (minMarksSum == limitSum) {
            for (int i = 0; i < studCount; i++) {
                studMarks[i][1] = studMarks[i][0];
            }
            return findMedian(studMarks);
        }

        if (maxMarksSum <= limitSum) {
            for (int i = 0; i < studCount; i++) {
                studMarks[i][0] = studMarks[i][1];
            }
            return findMedian(studMarks);
        }

        int medianIndex = findMedianIndex(studMarks);
        medianValue = studMarks[medianIndex][1];
        int diff = maxMarksSum - limitSum;

        diff -= setMarksLessThanMedianToMin(studMarks, medianIndex);
        int minMark = findMinMark(studMarks, medianIndex, studCount);

        while (diff > 0 && medianValue != minMark) {
            int right = studCount - 1;
            while (right > medianIndex) {
                if (diff == 0) {
                    return medianValue;
                }
                if (studMarks[right][1] == studMarks[right][0] || studMarks[right][1] == medianValue) {
                    right--;
                } else {
                    if (studMarks[right][1] - 1 >= studMarks[right - 1][1] && studMarks[right][1] >= medianValue) {
                        studMarks[right][1]--;
                        diff--;
                    } else {
                        right--;
                    }
                }
            }
            if (studMarks[medianIndex][1] - 1 >= studMarks[medianIndex][0]) {
                studMarks[medianIndex][1]--;
                medianValue = studMarks[medianIndex][1];
                diff--;
            } else {
                right = studCount - 1;
                while (right > medianIndex) {
                    if (studMarks[right][1] != studMarks[right][0]) {
                        if (studMarks[right][1] - 1 >= studMarks[right][0]) {
                            studMarks[right][1]--;
                            diff--;
                            if (studMarks[right][1] < medianValue) {
                                medianValue = findMedian(studMarks);
                                diff -= setMarksLessThanMedianToMin(studMarks, medianIndex);
                                medianValue = findMedian(studMarks);
                                break;
                            }
                        }
                    }
                    right--;
                }
            }
        }
        return medianValue;
    }

    private static int findMinMark(int[][] marks, int from, int to) {
        int minMark = Integer.MAX_VALUE;
        for (int i = from; i < to; i++) {
            minMark = Math.min(minMark, marks[i][0]);
        }
        return minMark;
    }

    private static int setMarksLessThanMedianToMin(int[][] marks, int medianIndex) {
        int howManyDecreased = 0;
        for (int i = 0; i < medianIndex; i++) {
            howManyDecreased += marks[i][1] - marks[i][0];
            marks[i][1] = marks[i][0];
        }
        return howManyDecreased;
    }

    public static int findMedianIndex(int[][] marks) {
        if (marks == null || marks.length <= 1) {
            return 0;
        }
        int n = marks.length;
        return quickSelectIndex(marks, 0, n - 1, (n - 1) / 2);
    }

    public static int findMedian(int[][] marks) {
        int medianIndex = findMedianIndex(marks);
        return marks[medianIndex][1];
    }

    private static int quickSelectIndex(int[][] nums, int start, int end, int target) {
        int index = partition(nums, start, end);
        if (index == target) {
            return index;
        } else if (index < target) {
            return quickSelectIndex(nums, index + 1, end, target);
        }
        else {
            return quickSelectIndex(nums, start, index - 1, target);
        }
    }

    private static int partition(int[][] nums, int low, int high) {
        int pivotIndex = high;
        int pivot = nums[pivotIndex][1];

        while (low < high) {
            while (low < high && nums[low][1] < pivot) {
                low++;
            }
            while (low < high && nums[high][1] >= pivot) {
                high--;
            }
            swap(nums, low, high);
        }

        if (nums[low][0] >= nums[pivotIndex][0]) {
            swap(nums, low, pivotIndex);
        }

        return low;
    }

    private static void swap(int[][] nums, int x, int y) {
        int[] temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

}
