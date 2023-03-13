package task2;

public class Solution {

    public static int time(int juniors, int seniors, int reviews) {
        int totalTime = 0;
        int availableSeniors = seniors;
        int reqReviews;
        for (int i = 1; i <= juniors; i++) {
            reqReviews = reviews;

            if (reqReviews == availableSeniors) {
                totalTime++;
                availableSeniors = seniors;
                continue;
            }

            if (reqReviews > availableSeniors) {
                reqReviews -= availableSeniors;
                totalTime++;
                availableSeniors = seniors;
            }

            if (reqReviews < availableSeniors) {
                if (i == juniors) {
                    totalTime += 1;
                    break;
                } else {
                    availableSeniors -= reqReviews;
                }
            }
        }
        return totalTime;
    }

    public static void main(String[] args) {
        System.out.println(time(3,2,2));
        System.out.println(time(7,3,2));
        System.out.println(time(10000,10000,10000));
        System.out.println(time(10000,10000,10000));
    }

}
