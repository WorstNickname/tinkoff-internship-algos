package task6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    public static int findMaxMedian(int studCount,
                                    int limitSum,
                                    int[][] studMarks,
                                    int minMarksSum,
                                    int maxMarksSum) {
        int medianValue = -1;

        if (minMarksSum > limitSum) {
            return medianValue;
        } else if (minMarksSum == limitSum) {
            sortByMinMark(studMarks);
            int medianIndex = findMedianIndex(studMarks);
            medianValue = studMarks[medianIndex][0];
            return medianValue;
        }

        sortByMaxMark(studMarks, 0, studCount);

        int medianIndex = findMedianIndex(studMarks);
        int delta = maxMarksSum - limitSum;
        medianValue = studMarks[medianIndex][1];
        if (delta <= 0) {
            return medianValue;
        }

        delta -= decreaseMarksBeforeMedianToMin(studMarks, medianIndex);

        if (delta <= 0) {
            return medianValue;
        } else {
            int minMark = findMinMark(studMarks, medianIndex, studCount);

            OUTER:
            while (delta > 0 && medianValue != minMark) {
                int right = studMarks.length - 1;
                while (right > medianIndex) {
                    if (delta == 0) {
                        break OUTER;
                    }
                    if (studMarks[right][1] == studMarks[right][0]
                            || studMarks[right][1] == medianValue) {
                        right--;
                    } else {
                        if (studMarks[right][1] - 1 >= studMarks[right - 1][1]
                                || studMarks[right][1] >= medianValue) {
                            studMarks[right][1]--;
                            delta--;
                        } else {
                            right--;
                        }
                    }
                }

                if (studMarks[medianIndex][1] - 1 >= studMarks[medianIndex][0]) {
                    studMarks[medianIndex][1]--;
                    medianValue = studMarks[medianIndex][1];
                    delta--;
                } else {
                    right = studMarks.length - 1;
                    while (right > medianIndex) {
                        if (studMarks[right][1] != studMarks[right][0]) {
                            if (studMarks[right][1] - 1 >= studMarks[right][0]) {
                                studMarks[right][1]--;
                                delta--;
                                sortByMaxMark(studMarks, medianIndex, studCount - 1);
                                medianValue = studMarks[findMedianIndex(studMarks)][1];
                                break;
                            }
                        }
                        right--;
                    }
                }
            }
        }
        return medianValue;
    }

    private static void sortByMaxMark(int[][] marks, int from, int to) {
        Arrays.sort(marks, from, to, Comparator.comparingInt((int[] a) -> a[1])
                .thenComparingInt(a -> a[0]));
    }

    private static void sortByMinMark(int[][] marks) {
        Arrays.sort(marks, Comparator.comparingInt(ints -> ints[0]));
    }

    private static int decreaseMarksBeforeMedianToMin(int[][] marks, int median) {
        int howManyDecreased = 0;
        for (int i = 0; i < median; i++) {
            howManyDecreased += marks[i][1] - marks[i][0];
            marks[i][1] = marks[i][0];
        }
        return howManyDecreased;
    }

    private static int findMedianIndex(int[][] array) {
        return (array.length - 1) / 2;
    }

    private static int findMinMark(int[][] marks, int from, int to) {
        int minMark = Integer.MAX_VALUE;
        for (int i = from; i < to; i++) {
            minMark = Math.min(minMark, marks[i][0]);
        }
        return minMark;
    }

    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            String[] split = line.split("\\s");
            int studCount = Integer.parseInt(split[0]);
            int limitSum = Integer.parseInt(split[1]);

            int[][] studMarks = new int[studCount][2];
            int sumMin = 0, sumMax = 0;
            for (int i = 0; i < studCount; i++) {
                split = reader.readLine().split("\\s");
                studMarks[i][0] = Integer.parseInt(split[0]);
                studMarks[i][1] = Integer.parseInt(split[1]);
                sumMin += studMarks[i][0];
                sumMax += studMarks[i][1];
            }
            System.out.println(findMaxMedian(studCount, limitSum, studMarks, sumMin, sumMax));
        }
    }
//        Scanner scanner = new Scanner(System.in);
//        int studCount = scanner.nextInt();
//        int limitSum = scanner.nextInt();
//
//        int[][] studMarks = new int[studCount][2];
//        int sumMin = 0;
//        int sumMax = 0;
//        for (int i = 0; i < studCount; i++) {
//            int min = scanner.nextInt();
//            int max = scanner.nextInt();
//            studMarks[i][0] = min;
//            studMarks[i][1] = max;
//            sumMin += min;
//            sumMax += max;
//
//        findMaxMedian(studCount, limitSum, studMarks);
}


