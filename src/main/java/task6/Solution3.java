package task6;

import java.util.Arrays;
import java.util.Comparator;

public class Solution3 {

    public static int findMaxMedian(int studCount, int limitSum, int[][] studMarks) {
        int minMarksSum = 0;
        int maxMarksSum = 0;
        int medianValue = -1;
        for (int i = 0; i < studCount; i++) {
            minMarksSum += studMarks[i][0];
            maxMarksSum += studMarks[i][1];
        }

        // check min boundary constraints
        if (minMarksSum > limitSum) {
            return medianValue;
        } else if (minMarksSum == limitSum) {
            // вернуть медиану в массиве из мин чисел
            sortArrayByMinMark(studMarks);
            int medianIndex = findMedianIndex(studMarks);
            medianValue = studMarks[medianIndex][0];
            return medianValue;
        }
        // Сортируем по-большому
        sortArrayByMaxMark(studMarks);

        int medianIndex = findMedianIndex(studMarks);
        int delta = limitSum - maxMarksSum;
        medianValue = studMarks[medianIndex][1];
        if (delta >= 0) {
            return medianValue;
        }
        // Срезаем все, что идет в массиве до медианы до min
        delta += decreaseMarksBeforeMedianToMin(studMarks, medianIndex);

        if (delta >= 0) {
            return medianValue;
        } else {
            int minMark = findMinMark(studMarks, medianIndex, studCount);

            OUTER:
            while (delta <= 0 && medianValue != minMark) {
                int right = studMarks.length - 1;
                if (delta == 0) {
                    break;
                }
                while (right > medianIndex) {
                    if (delta == 0) {
                        break OUTER;
                    }
                    if (studMarks[right][1] == studMarks[right][0] || studMarks[right][1] == medianValue) {
                        right--;
                    } else {
                        if (studMarks[right][1] - 1 >= studMarks[right - 1][1] || studMarks[right][1] >= medianValue) {
                            studMarks[right][1]--;
                            delta++;
                        } else {
                            right--;
                        }
                    }
                }

                if (studMarks[medianIndex][1] - 1 >= studMarks[medianIndex][0]) {
                    studMarks[medianIndex][1]--;
                    medianValue = studMarks[medianIndex][1];
                    delta++;
                } else {
                    right = studMarks.length - 1;
                    while (right > medianIndex) {
                        if (studMarks[right][1] == studMarks[right][0]) {
                            right--;
                        } else {
                            if (studMarks[right][1] - 1 >= studMarks[right][0]) {
                                studMarks[right][1]--;
                                delta++;
                                sortArrayByMaxMark(studMarks, medianIndex, studMarks.length - 1);
                                medianValue = studMarks[findMedianIndex(studMarks)][1];
                                break;
                            }
                            right--;
                        }
                    }

                }
            }
        }
        return medianValue;
    }

    private static void sortArrayByMaxMark(int[][] marks) {
        Arrays.sort(marks, Comparator.comparingInt((int[] a) -> a[1]).thenComparingInt(a -> a[0]));
    }

    private static void sortArrayByMaxMark(int[][] marks, int from, int to) {
        Arrays.sort(marks, from, to, Comparator.comparingInt((int[] a) -> a[1]).thenComparingInt(a -> a[0]));
    }

    private static void sortArrayByMinMark(int[][] marks) {
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

    public static void main(String[] args) {
//        case 1
        int studCount = 7;
        int limitSum = 42;
        int[][] studMarks = {{5, 5}, {3, 5}, {7, 9}, {6, 7}, {3, 8}, {10, 10}, {1, 1}};

        //case 2
//        int studCount = 3;
//        int limitSum = 27;
//        int[][] studMarks = {{11, 14}, {2, 10}, {11, 14}};

        findMaxMedian(studCount, limitSum, studMarks);
    }


//    Scanner scanner = new Scanner(System.in);
//
//    int studCount = scanner.nextInt();
//    int limit = scanner.nextInt();
//
//    int[][] studMarks = new int[studCount][2];
//    int sumMin = 0;
//    int sumMax = 0;
//        for (int i = 0; i < studCount; i++) {
//        int min = scanner.nextInt();
//        int max = scanner.nextInt();
//        studMarks[i][0] = min;
//        studMarks[i][1] = max;
//        sumMin += min;
//        sumMax += max;
//    }
}
