package task6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution4 {


    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            String[] split = line.split("\\s");
            int studCount = Integer.parseInt(split[0]);
            int limitSum = Integer.parseInt(split[1]);

            int[] studMarksMin = new int[studCount];
            int[] studMarksMax = new int[studCount];

            int sumMin = 0, sumMax = 0;
            for (int i = 0; i < studCount; i++) {
                split = reader.readLine().split("\\s");
                studMarksMin[i] = Integer.parseInt(split[0]);
                studMarksMax[i] = Integer.parseInt(split[1]);
                sumMin += studMarksMin[i];
                sumMax += studMarksMax[i];
            }
        }
    }

    public static int findMaxMedian(int studCount,
                                    int limitSum,
                                    int[] studMarksMin,
                                    int[] studMarksMax,
                                    int minMarksSum,
                                    int maxMarksSum) {
        int medianValue = -1;

        // Check min constraints
        if (minMarksSum > limitSum) {
            return medianValue;
        } else if (minMarksSum == limitSum) {
            return median(studMarksMin);
        }

        medianValue = median(studMarksMax);
        int delta = maxMarksSum - limitSum;
        if (delta <= 0) {
            return medianValue;
        }

        // Decrease all marks less than to MIN mark
//        delta -= setMarksLessThanMedianToMin(studMarksMax, medianValue);


        return medianValue;
    }

    private static int setMarksLessThanMedianToMin(int[] studMarksMax,
                                                   int[] studMarksMin,
                                                   int medianValue) {
        for (int i = 0; i < studMarksMax.length; i++) {
            if (studMarksMax[i] < medianValue) {
                studMarksMax[i] = studMarksMin[i];
            }
        }
        return -1;
    }






    public static int median(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int n = nums.length;
        return quickSelect(nums, 0, n - 1, (n - 1) / 2);
    }

    private static int quickSelect(int[] nums, int start, int end, int target) {
        int index = partition(nums, start, end);
        if (index == target) return nums[index];
        else if (index < target) return quickSelect(nums, index + 1, end, target);
        else return quickSelect(nums, start, index - 1, target);
    }

    private static int partition(int[] nums, int low, int high) {
        int pivotIndex = high;
        int pivot = nums[pivotIndex];

        while (low < high) {
            while (low < high && nums[low] < pivot)
                low++;
            while (low < high && nums[high] >= pivot)
                high--;
            swap(nums, low, high);
        }

        swap(nums, low, pivotIndex);

        return low;
    }

    private static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

}
