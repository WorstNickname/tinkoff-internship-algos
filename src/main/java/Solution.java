import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static int price(int pricePerMonth, int size, int pricePerMb, int spent) {
        int result = 0;
        if (spent <= size) {
            result = pricePerMonth;
        } else {
            int diff = spent - size;
            result = pricePerMonth + pricePerMb * diff;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] strings = reader.readLine().split(" ");
            int pricePerMonth = Integer.parseInt(strings[0]);
            int size = Integer.parseInt(strings[1]);
            int pricePerMb = Integer.parseInt(strings[2]);
            int spent = Integer.parseInt(strings[3]);
            System.out.println(price(pricePerMonth, size, pricePerMb, spent));
        }
    }
}