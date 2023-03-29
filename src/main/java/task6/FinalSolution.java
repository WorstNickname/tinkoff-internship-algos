package task6;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FinalSolution {

    public static int findMaxMedian(int studCount,
                                    int limitSum,
                                    int[][] marks,
                                    int minMarksSum,
                                    int maxMarksSum) {
        int medianValue = -1;

        if (minMarksSum > limitSum) {
            return medianValue;
        } else if (minMarksSum == limitSum) {
            for (int i = 0; i < studCount; i++) {
                marks[i][1] = marks[i][0];
            }
            return findMedian(marks);
        }

        if (maxMarksSum <= limitSum) {
            for (int i = 0; i < studCount; i++) {
                marks[i][0] = marks[i][1];
            }
            return findMedian(marks);
        }

        int medianIndex = findMedianIndex(marks);
        medianValue = marks[medianIndex][1];
        int diff = maxMarksSum - limitSum;

        diff -= setMarksLessThanMedianToMin(marks, medianIndex);
        int minMark = findMinMark(marks, medianIndex, studCount);

        while (diff > 0 && medianValue != minMark) {
            int right = studCount - 1;
            while (right > medianIndex) {
                if (diff == 0) {
                    return medianValue;
                }
                if (marks[right][1] == marks[right][0]
                        || marks[right][1] == medianValue) {
                    right--;
                } else {
                    if (marks[right][1] - 1 >= marks[right - 1][1]
                            && marks[right][1] >= medianValue) {
                        marks[right][1]--;
                        diff--;
                    } else {
                        right--;
                    }
                }
            }
            if (marks[medianIndex][1] - 1 >= marks[medianIndex][0]) {
                marks[medianIndex][1]--;
                medianValue = marks[medianIndex][1];
                diff--;
            } else {
                right = studCount - 1;
                while (right > medianIndex) {
                    if (marks[right][1] != marks[right][0]) {
                        if (marks[right][1] - 1 >= marks[right][0]) {
                            marks[right][1]--;
                            diff--;
                            if (marks[right][1] < medianValue) {
                                medianValue = findMedian(marks);
                                diff -= setMarksLessThanMedianToMin(marks, medianIndex);
                                medianValue = findMedian(marks);
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

    private static int findMinMark(int[][] marks,
                                   int from,
                                   int to) {
        int minMark = Integer.MAX_VALUE;
        for (int i = from; i < to; i++) {
            minMark = Math.min(minMark, marks[i][0]);
        }
        return minMark;
    }

    private static int setMarksLessThanMedianToMin(int[][] marks,
                                                   int medianIndex) {
        int howManyDecreased = 0;
        for (int i = 0; i < medianIndex; i++) {
            howManyDecreased += marks[i][1] - marks[i][0];
            marks[i][1] = marks[i][0];
        }
        return howManyDecreased;
    }

    private static int findMedianIndex(int[][] array) {
        if (array == null || array.length <= 1) {
            return 0;
        }
        int l = array.length;
        return quickSelectIndex(array, 0, l - 1, (l - 1) / 2);
    }

    private static int findMedian(int[][] array) {
        int medianIndex = findMedianIndex(array);
        return array[medianIndex][1];
    }

    private static int quickSelectIndex(int[][] array,
                                        int start,
                                        int end,
                                        int target) {
        int index = divideArray(array, start, end);
        if (index == target) {
            return index;
        } else if (index < target) {
            return quickSelectIndex(array, index + 1, end, target);
        } else {
            return quickSelectIndex(array, start, index - 1, target);
        }
    }

    private static int divideArray(int[][] array,
                                   int low,
                                   int high) {
        int pivotIndex = high;
        int pivot = array[pivotIndex][1];

        while (low < high) {
            while (low < high && array[low][1] < pivot) {
                low++;
            }
            while (low < high && array[high][1] >= pivot) {
                high--;
            }
            swapArray(array, low, high);
        }

        if (array[low][0] >= array[pivotIndex][0]) {
            swapArray(array, low, pivotIndex);
        }

        return low;
    }

    private static void swapArray(int[][] array,
                                  int first,
                                  int second) {
        int[] temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public static void main(String[] args) throws IOException {

        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            String[] nums = line.split("\\s");
            int n = Integer.parseInt(nums[0]);
            int s = Integer.parseInt(nums[1]);

            int[][] marks = new int[n][2];
            int sumMin = 0, sumMax = 0;
            for (int i = 0; i < n; i++) {
                nums = reader.readLine().split("\\s");
                marks[i][0] = Integer.parseInt(nums[0]);
                marks[i][1] = Integer.parseInt(nums[1]);
                sumMin += marks[i][0];
                sumMax += marks[i][1];
            }

            System.out.println(findMaxMedian(n, s, marks, sumMin, sumMax));
        }
    }

}