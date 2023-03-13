package task4;

public class Solution {

    public static int findMaxBoringPrefix(int length, int[] nums) {
        int[] digitMap = new int[200001];
        int[] freqMap = new int[length + 1];

        for (int i = 0; i < length; i++) {
            digitMap[nums[i]]++;
            freqMap[digitMap[nums[i]]]++;
        }

        for (int i = length - 1; i > 0; i--) {
            if (digitMap[nums[i]] * freqMap[digitMap[nums[i]]] == i) {
                return i + 1;
            } else {
                freqMap[digitMap[nums[i]]]--;
                digitMap[nums[i]]--;
                if (digitMap[nums[i - 1]] * freqMap[digitMap[nums[i - 1]]] == i) {
                    return i + 1;
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        System.out.println(findMaxBoringPrefix(13, new int[]{1, 2, 3, 1, 2, 2, 3, 3, 3, 1, 4, 4, 5}));
        System.out.println(findMaxBoringPrefix(10, new int[]{1, 2, 4, 2, 3, 1, 3, 9, 15, 23}));
        System.out.println(findMaxBoringPrefix(5, new int[]{1, 2, 3, 4, 5}));
        System.out.println(findMaxBoringPrefix(9, new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3}));
        System.out.println(findMaxBoringPrefix(11, new int[]{1, 1, 1, 1, 1, 2, 3, 2, 3, 2, 3}));
        System.out.println(findMaxBoringPrefix(3, new int[]{5, 5, 5}));
        System.out.println(findMaxBoringPrefix(7, new int[]{7, 7, 7, 7, 7, 7, 7}));
    }
}
